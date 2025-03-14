package com.mercadolibre.mobilechallenge.domain.usecase

import com.mercadolibre.mobilechallenge.data.network.exception.ApiError


interface ServiceUseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}