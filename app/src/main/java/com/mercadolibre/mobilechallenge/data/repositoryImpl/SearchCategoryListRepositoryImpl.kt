package com.mercadolibre.mobilechallenge.data.repositoryImpl

import com.mercadolibre.mobilechallenge.data.network.MercadoApi
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryList
import com.mercadolibre.mobilechallenge.domain.repository.SearchCategoryListRepository
import javax.inject.Inject

class SearchCategoryListRepositoryImpl @Inject constructor(
    private val retrofitInstance: MercadoApi,
): SearchCategoryListRepository {

    override suspend fun getSearchCategoryList(query: String): List<ResponseCategoryList> {
        return retrofitInstance.getSearchCategoryList(query)
    }
}