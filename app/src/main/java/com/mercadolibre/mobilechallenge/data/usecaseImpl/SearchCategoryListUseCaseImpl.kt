package com.mercadolibre.mobilechallenge.data.usecaseImpl

import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryList
import com.mercadolibre.mobilechallenge.domain.repository.SearchCategoryListRepository
import com.mercadolibre.mobilechallenge.domain.usecase.ServiceUseCase
import javax.inject.Inject

class SearchCategoryListUseCaseImpl @Inject constructor(
    private val searchCategoryListRepository: SearchCategoryListRepository
): ServiceUseCase<List<ResponseCategoryList>, Any?>() {
    override suspend fun run(request: Any?): List<ResponseCategoryList> = searchCategoryListRepository.getSearchCategoryList(
        request.toString()
    )
}