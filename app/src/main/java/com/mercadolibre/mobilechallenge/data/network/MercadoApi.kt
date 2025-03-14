package com.mercadolibre.mobilechallenge.data.network

import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryItem
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz mediante la cual se definen los metodos de consulta al API
 */
interface MercadoApi {

    /**
     * Funcion que busca las dominios
     */
    @GET("sites/MLA/domain_discovery/search")
    suspend fun getSearchCategoryList(
        @Query("q") query: String
    ): List<ResponseCategoryList>

    /**
     * Funcion que busca categoria
     */
    @GET("categories/{categoryId}")
    suspend fun getSearchCategoryItem(
        @Path("categoryId") categoryId: String
    ): ResponseCategoryItem

}