package com.mercadolibre.mobilechallenge.ui.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Callback para cuando sucede un evento en cada item
 */
typealias BindCallback<T> = (view : View, data : T, position : Int) -> Unit

/** Adaptador generico para mostrar listas con contenedores de tipo RecyclerView */
class AdapterCategoryList<T>(
    private var dataset: List<T>,
    private val itemLayoutId: Int? = null,
    private val itemViewFactory: (() -> View)? = null,
    private val onBind: BindCallback<T>
) : RecyclerView.Adapter<AdapterCategoryList.GenericListViewHolder>() {

    class GenericListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GenericListViewHolder(when {
        itemViewFactory != null -> itemViewFactory.invoke()
        itemLayoutId != null -> {
            LayoutInflater.from(parent.context)
                .inflate(itemLayoutId, parent, false)
        }
        else -> {
            throw IllegalStateException(
                "ID de layout no valido")
        }
    })

    override fun onBindViewHolder(holder: GenericListViewHolder, position: Int) {
        if (position < 0 || position > dataset.size) return
        onBind(holder.view, dataset[position], position)
    }

    /**
     * Funcion que actualiza la lista
     */
    fun updateListCategory(list: List<T>){
        this.dataset = list
        notifyDataSetChanged()
    }

    /**
     * Funcion que limpia la lista
     */
    fun clearList() {
        dataset = emptyList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.size
}