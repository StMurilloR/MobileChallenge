package com.mercadolibre.mobilechallenge.data.usecaseImpl

import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryItem
import com.mercadolibre.mobilechallenge.domain.repository.SearchCategoryItemRepository
import com.mercadolibre.mobilechallenge.domain.usecase.ServiceUseCase
import javax.inject.Inject

class SearchCategoryItemUseCaseImpl @Inject constructor(
    private val searchCategoryItemRepository: SearchCategoryItemRepository
): ServiceUseCase<ResponseCategoryItem, Any?>() {
    override suspend fun run(request: Any?): ResponseCategoryItem = searchCategoryItemRepository.getSearchCategoryItem(
        request.toString()
    )
}