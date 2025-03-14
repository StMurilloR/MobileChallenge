package com.mercadolibre.mobilechallenge.domain.repository

import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryItem


interface SearchCategoryItemRepository {

    suspend fun getSearchCategoryItem(categoryId: String): ResponseCategoryItem
}