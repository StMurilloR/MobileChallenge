package com.mercadolibre.mobilechallenge.ui.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mercadolibre.mobilechallenge.ui.presentation.adapters.AdapterCategoryList
import com.mercadolibre.mobilechallenge.ui.presentation.adapters.CategoryInflater
import com.mercadolibre.mobilechallenge.R
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryList
import com.mercadolibre.mobilechallenge.databinding.FragmentResultSearchBinding
import com.mercadolibre.mobilechallenge.ui.viewmodel.SearchViewModel
import com.mercadolibre.mobilechallenge.utils.Constants.CATEGORY_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultSearchFragment : Fragment() {

    private var _binding: FragmentResultSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by activityViewModels()
    private var loadingDialog: AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResultSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.categoryRecyclerFilter.visibility = View.GONE
        binding.emptyItemsTextFilter.visibility = View.VISIBLE
        binding.textInputSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.getSearchCategoryList(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Funcion que inicializa el observador
     */
    private fun initObservers() {
        searchViewModel.responseCategoryList.observe(viewLifecycleOwner) { data ->
            initCategoryAdapter(data)
            if (data.isNotEmpty()) {
                binding.categoryRecyclerFilter.visibility = View.VISIBLE
                binding.emptyItemsTextFilter.visibility = View.GONE
            } else {
                binding.categoryRecyclerFilter.visibility = View.GONE
                binding.emptyItemsTextFilter.visibility = View.VISIBLE
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

    /**
     * Funcion que inicializa el adaptador de los beneficios
     */
    private fun initCategoryAdapter(listCategory: List<ResponseCategoryList>) {
        binding.categoryRecyclerFilter.adapter = AdapterCategoryList(
            dataset = listCategory,
            itemViewFactory = {
                CategoryInflater(this.requireContext())
            }
        ) { view, data, _ ->
            setData(view as CategoryInflater, data)
        }
        binding.categoryRecyclerFilter.layoutManager = GridLayoutManager(this.context, 1)
    }

    /**
     * Asigna los datos a la vista
     */
    private fun setData(categoryItemInflater: CategoryInflater, data: ResponseCategoryList) {
        with((categoryItemInflater).binding) {
            categoryName.text = data.category_name.toString()
            domainName.text = data.domain_name.toString()
            val bundle = Bundle()
            bundle.putString(CATEGORY_ID, data.category_id)
            categoryItemInflater.setOnClickListener {
                findNavController().navigate(R.id.action_resultSearchFragment_to_detailCategoryItemFragment, bundle)
            }
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            if (loadingDialog == null) {
                loadingDialog = AlertDialog.Builder(this.requireContext())
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