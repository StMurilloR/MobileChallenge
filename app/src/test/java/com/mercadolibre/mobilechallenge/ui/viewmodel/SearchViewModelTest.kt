package com.mercadolibre.mobilechallenge.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryItem
import com.mercadolibre.mobilechallenge.data.model.ResponseCategoryList
import com.mercadolibre.mobilechallenge.data.network.exception.ApiError
import com.mercadolibre.mobilechallenge.data.network.exception.ApiError.ErrorStatus
import com.mercadolibre.mobilechallenge.data.usecaseImpl.SearchCategoryItemUseCaseImpl
import com.mercadolibre.mobilechallenge.data.usecaseImpl.SearchCategoryListUseCaseImpl
import com.mercadolibre.mobilechallenge.domain.usecase.ServiceUseCaseResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val searchCategoryListUseCaseImpl: SearchCategoryListUseCaseImpl = mockk()
    private val searchCategoryItemUseCaseImpl: SearchCategoryItemUseCaseImpl = mockk()
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(searchCategoryListUseCaseImpl, searchCategoryItemUseCaseImpl)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getSearchCategoryList should update responseCategoryList on success`() = runTest {
        val query = "test"
        val mockResponse = listOf(ResponseCategoryList("1", "Category 1", "1" ,"1", emptyList()))

        coEvery {
            searchCategoryListUseCaseImpl.invoke(eq(query), any())
        } answers {
            val callback = arg<ServiceUseCaseResponse<List<ResponseCategoryList>>>(1)
            callback.onSuccess(mockResponse)
        }

        viewModel.getSearchCategoryList(query)

        coVerify {
            searchCategoryListUseCaseImpl.invoke(eq(query), any())
        }
        assert(viewModel.responseCategoryList.value == mockResponse)
        assert(viewModel.showOrHideLoader.value == false)
        assert(viewModel.errorMessage.value == null)
    }

    @Test
    fun `getSearchCategoryList should update errorMessage on error`() = runTest {

        val query = "test"
        val mockError = ApiError("Error message", ErrorStatus.BAD_REQUEST)

        coEvery {
            searchCategoryListUseCaseImpl.invoke(eq(query), any())
        } answers {
            val callback = arg<ServiceUseCaseResponse<ResponseCategoryList>>(1)
            callback.onError(mockError)
        }

        viewModel.getSearchCategoryList(query)

        coVerify {
            searchCategoryListUseCaseImpl.invoke(eq(query), any())
        }
        assert(viewModel.responseCategoryList.value == null)
        assert(viewModel.showOrHideLoader.value == false)
        assert(viewModel.errorMessage.value == mockError.getErrorMessage())
    }

    @Test
    fun `getSearchCategoryItem should update responseCategoryItem on success`() = runTest {
        val query = "test"
        val mockResponse = ResponseCategoryItem("1", "Category 1",
            "Description", "", Long.MAX_VALUE, emptyList(), emptyList(),
            "",null, emptyList(),"",false,"")

        coEvery {
            searchCategoryItemUseCaseImpl.invoke(eq(query), any())
        } answers {
            val callback = arg<ServiceUseCaseResponse<ResponseCategoryItem>>(1)
            callback.onSuccess(mockResponse)
        }

        viewModel.getSearchItemCategory(query)

        coVerify {
            searchCategoryItemUseCaseImpl.invoke(eq(query), any())
        }
        assert(viewModel.responseCategoryItem.value == mockResponse)
        assert(viewModel.showOrHideLoader.value == false)
        assert(viewModel.errorMessage.value == null)
    }

    @Test
    fun `getSearchCategoryItem should update errorMessage on error`() = runTest {
        val query = "test"
        val mockError = ApiError("Error message", ErrorStatus.BAD_REQUEST)

        coEvery {
            searchCategoryItemUseCaseImpl.invoke(eq(query), any())
        } answers {
            val callback = arg<ServiceUseCaseResponse<ResponseCategoryItem>>(1)
            callback.onError(mockError)
        }

        viewModel.getSearchItemCategory(query)

        coVerify {
            searchCategoryItemUseCaseImpl.invoke(eq(query), any())
        }
        assert(viewModel.responseCategoryItem.value == null)
        assert(viewModel.showOrHideLoader.value == false)
        assert(viewModel.errorMessage.value == mockError.getErrorMessage())
    }
}
