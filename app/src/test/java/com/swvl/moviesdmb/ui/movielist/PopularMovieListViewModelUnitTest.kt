package com.swvl.moviesdmb.ui.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.swvl.moviesdmb.models.AbstractProxyGetRepository
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.movielist.adapter.MovieItemViewModel
import com.swvl.moviesdmb.ui.utils.Resource
import com.swvl.moviesdmb.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularMovieListViewModelUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var movieRepositoryMock: AbstractProxyGetRepository<Movie,Movie>

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<List<MovieItemViewModel>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            //Arrange Given
            Mockito.doReturn(emptyList<Movie>())
                .`when`(movieRepositoryMock)
                .getAllById(anyInt())

            //Action When
            val viewModel = PopularMovieListViewModel(movieRepositoryMock)
            viewModel.moviesItem.observeForever(apiUsersObserver)
            //Result then Assertion
            Mockito.verify(movieRepositoryMock).getAllById(anyInt())
            Mockito.verify(apiUsersObserver).onChanged(Resource.success(emptyList()))
            viewModel.moviesItem.removeObserver(apiUsersObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            //Arrange Given
            val errorMessage = "Error Message For You"
            Mockito.doThrow(RuntimeException(errorMessage))
                .`when`(movieRepositoryMock)
                .getAllById(anyInt())

            //Action When
            val viewModel = PopularMovieListViewModel(movieRepositoryMock)
            viewModel.moviesItem.observeForever(apiUsersObserver)
            //Result then Assertion
            Mockito.verify(movieRepositoryMock).getAllById(anyInt())
            Mockito.verify(apiUsersObserver).onChanged(
                Resource.error(
                    null, errorMessage
                )
            )
            viewModel.moviesItem.removeObserver(apiUsersObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}