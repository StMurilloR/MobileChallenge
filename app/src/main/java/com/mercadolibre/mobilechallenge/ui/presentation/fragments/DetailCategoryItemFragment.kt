package com.mercadolibre.mobilechallenge.ui.presentation.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mercadolibre.mobilechallenge.R
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryItem
import com.mercadolibre.mobilechallenge.databinding.FragmentDetailCategoryItemBinding
import com.mercadolibre.mobilechallenge.ui.viewmodel.SearchViewModel
import com.mercadolibre.mobilechallenge.utils.Constants.CATEGORY_ID

class DetailCategoryItemFragment: Fragment() {

    private var _binding: FragmentDetailCategoryItemBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by activityViewModels()
    private var loadingDialog: AlertDialog? = null
    private var categoryId: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailCategoryItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryId = arguments?.getString( CATEGORY_ID).toString()
        searchViewModel.getSearchItemCategory(categoryId)
        binding.cardContainerDetail.visibility = View.GONE
        initObservers()
        binding.goToHomeButton.setOnClickListener {
            searchViewModel.clearResponseCategoryList()
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        searchViewModel.responseCategoryItem.observe(viewLifecycleOwner) {
            it?.let {
                binding.cardContainerDetail.visibility = View.VISIBLE
                updateUI(it)
            }
        }
        searchViewModel.showOrHideLoader.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
        searchViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                showErrorDialog(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(category: ResponseCategoryItem) {
        with(binding){
            if (category.picture != null) {
                Glide.with(this@DetailCategoryItemFragment)
                    .load(category.picture)
                    .into(imageCategory)
            } else {
                Glide.with(this@DetailCategoryItemFragment)
                    .load(R.drawable.image_not_found)
                    .into(imageCategory)
            }
            val categoryName = category.name ?: getString(R.string.not_name)
            textViewCategoryName.text = categoryName
            val totalItems = category.total_items_in_this_category ?: 0
            textViewTotalItems.text = getString(R.string.total_items)  + " " + totalItems
            val pathFromRoot = if (category.path_from_root != null) {
                category.path_from_root.joinToString(" > ") { it?.name.toString() }
            } else {
                getString(R.string.not_route)
            }
            textViewPathFromRoot.text = getString(R.string.route) + " " + pathFromRoot


            val buyingModes = category.settings?.buying_modes?.joinToString(", ") ?: getString(R.string.not_available)
            textViewBuyingModes.text = getString(R.string.purchasing_modes) + " " + buyingModes


            val itemConditions = category.settings?.item_conditions?.joinToString(", ") ?: getString(R.string.not_available)
            textViewItemConditions.text = getString(R.string.conditions) + " " + itemConditions


            val minimumPrice = category.settings?.minimum_price ?: 0.0
            val minimumPriceCurrency = category.settings?.minimum_price_currency ?: ""
            textViewMinimumPrice.text = getString(R.string.minimum_price) + " " + minimumPrice + " "+ minimumPriceCurrency


            val maximumPrice = category.settings?.maximum_price ?: getString(R.string.not_register)
            val maximumPriceCurrency = category.settings?.maximum_price_currency ?: ""
            textViewMaximumPrice.text = getString(R.string.maximum_price) + " " + maximumPrice + " "+ maximumPriceCurrency


            val shippingOptions = category.settings?.shippingOptions?.joinToString(", ") ?: getString(R.string.not_available)
            textViewShippingOptions.text = getString(R.string.options_send) + " " + shippingOptions
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            if (loadingDialog == null) {
                loadingDialog = AlertDialog.Builder( this.requireContext())
                    .setView(R.layout.dialog_loading)
                    .setCancelable(false)
                    .create()
                loadingDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
            loadingDialog?.show()
        } else {
            loadingDialog?.dismiss()
        }
    }

    private fun showErrorDialog(message: String) {
        val dialog = AlertDialog.Builder(this.requireContext())
            .setTitle(R.string.error)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .show()

        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)
        dialog.show()
    }
}
