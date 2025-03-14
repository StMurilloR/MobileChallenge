package com.mercadolibre.mobilechallenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryItem
import com.mercadolibre.mobilechallenge.data.network.exception.ApiError
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryList
import com.mercadolibre.mobilechallenge.data.usecaseImpl.SearchCategoryItemUseCaseImpl
import com.mercadolibre.mobilechallenge.data.usecaseImpl.SearchCategoryListUseCaseImpl
import com.mercadolibre.mobilechallenge.domain.usecase.ServiceUseCaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCategoryListUseCaseImpl: SearchCategoryListUseCaseImpl,
    private val searchCategoryItemUseCaseImpl: SearchCategoryItemUseCaseImpl
)  : ViewModel() {

    private val _responseCategoryList = MutableLiveData<List<ResponseCategoryList>>()
    val responseCategoryList: LiveData<List<ResponseCategoryList>>
        get() = _responseCategoryList

    private val _responseCategoryItem = MutableLiveData<ResponseCategoryItem?>()
    val responseCategoryItem: LiveData<ResponseCategoryItem?>
        get() = _responseCategoryItem

    private val _showOrHideLoader = MutableLiveData<Boolean>()
    val showOrHideLoader: LiveData<Boolean>
        get() = _showOrHideLoader

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    /**
     * Función que busca el dominio
     */
    fun getSearchCategoryList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _showOrHideLoader.postValue(true)
            searchCategoryListUseCaseImpl.invoke(
                query,
                object : ServiceUseCaseResponse<List<ResponseCategoryList>> {
                    override fun onSuccess(result: List<ResponseCategoryList>) {
                        _showOrHideLoader.postValue(false)
                        _responseCategoryList.postValue( result)
                    }

                    override fun onError(apiError: ApiError?) {
                        _showOrHideLoader.postValue(false)
                        _errorMessage.postValue( apiError?.getErrorMessage())
                    }
                }
            )
        }
    }

    /**
     * Función que busca la categoria seleccionada
     */
    fun getSearchItemCategory(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _showOrHideLoader.postValue(true)
            searchCategoryItemUseCaseImpl.invoke(
                query,
                object : ServiceUseCaseResponse<ResponseCategoryItem> {
                    override fun onSuccess(result: ResponseCategoryItem) {
                        _showOrHideLoader.postValue(false)
                        _responseCategoryItem.postValue( result)
                    }

                    override fun onError(apiError: ApiError?) {
                        _showOrHideLoader.postValue(false)
                        _errorMessage.postValue( apiError?.getErrorMessage())
                    }
                }
            )
        }
    }

    /**
     * Función que resetea los observers
     */
    fun clearResponseCategoryList() {
        _responseCategoryList.value = emptyList()
        _responseCategoryItem.value = null
    }

}