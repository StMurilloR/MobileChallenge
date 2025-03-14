package com.mercadolibre.mobilechallenge.ui.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.widget.RadioGroup
import com.mercadolibre.mobilechallenge.databinding.CategoryListAdapterLayoutBinding

/**
 * Genera una instancia de tipo binding para el view que se le pase
 */
class CategoryInflater constructor(
    context: Context
): RadioGroup(context){
    val binding: CategoryListAdapterLayoutBinding = CategoryListAdapterLayoutBinding.inflate(
        LayoutInflater.from(context), this, true
    )
}