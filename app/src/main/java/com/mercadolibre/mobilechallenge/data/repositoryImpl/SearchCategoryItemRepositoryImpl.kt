package com.mercadolibre.mobilechallenge.data.repositoryImpl

import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryItem
import com.mercadolibre.mobilechallenge.domain.repository.SearchCategoryItemRepository
import com.mercadolibre.mobilechallenge.data.network.MercadoApi
import javax.inject.Inject

class SearchCategoryItemRepositoryImpl @Inject constructor(
    private val retrofitInstance: MercadoApi,
): SearchCategoryItemRepository {

    override suspend fun getSearchCategoryItem(categoryId: String): ResponseCategoryItem {
        return retrofitInstance.getSearchCategoryItem(categoryId)
    }
}