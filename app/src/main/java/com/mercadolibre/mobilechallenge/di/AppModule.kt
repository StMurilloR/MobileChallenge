package com.mercadolibre.mobilechallenge.di

import com.mercadolibre.mobilechallenge.domain.repository.SearchCategoryItemRepository
import com.mercadolibre.mobilechallenge.data.network.MercadoApi
import com.mercadolibre.mobilechallenge.domain.repository.SearchCategoryListRepository
import com.mercadolibre.mobilechallenge.data.repositoryImpl.SearchCategoryItemRepositoryImpl
import com.mercadolibre.mobilechallenge.data.usecaseImpl.SearchCategoryItemUseCaseImpl
import com.mercadolibre.mobilechallenge.data.repositoryImpl.SearchCategoryListRepositoryImpl
import com.mercadolibre.mobilechallenge.data.usecaseImpl.SearchCategoryListUseCaseImpl
import com.mercadolibre.mobilechallenge.ui.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provee la instancia del retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofit(): MercadoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MercadoApi::class.java)
    }

    /**
     * Provee la instancia del repositorio Category Item
     */
    @Provides
    @Singleton
    fun provideCategoryItemRepository(mercadoApi: MercadoApi): SearchCategoryItemRepository {
        return SearchCategoryItemRepositoryImpl(mercadoApi)
    }

    /**
     * Provee la instancia del Caso de uso para el Category Item
     */
    @Provides
    @Singleton
    fun provideCategoryItemUseCaseImpl(searchCategoryItemRepository: SearchCategoryItemRepository): SearchCategoryItemUseCaseImpl {
        return SearchCategoryItemUseCaseImpl(searchCategoryItemRepository)
    }

    /**
     * Provee la instancia del repositorio Category List
     */
    @Provides
    @Singleton
    fun provideCategoryListRepository(mercadoApi: MercadoApi): SearchCategoryListRepository {
        return SearchCategoryListRepositoryImpl(mercadoApi)
    }

    /**
     * Provee la instancia del Caso de uso para el Category List
     */
    @Provides
    @Singleton
    fun provideCategoryListUseCaseImpl(searchCategoryListRepository: SearchCategoryListRepository): SearchCategoryListUseCaseImpl {
        return SearchCategoryListUseCaseImpl(searchCategoryListRepository)
    }


    /**
     * Provee la instancia del viewModel
     */
    @Provides
    fun provideSearchViewModel(
        searchCategoryListUseCaseImpl: SearchCategoryListUseCaseImpl,
        searchCategoryItemUseCaseImpl: SearchCategoryItemUseCaseImpl,
    ): SearchViewModel {
        return SearchViewModel(searchCategoryListUseCaseImpl, searchCategoryItemUseCaseImpl)
    }
}