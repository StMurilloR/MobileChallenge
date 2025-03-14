package com.mercadolibre.mobilechallenge.domain.repository

import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryList

interface SearchCategoryListRepository {

    suspend fun getSearchCategoryList(query: String): List<ResponseCategoryList>
}