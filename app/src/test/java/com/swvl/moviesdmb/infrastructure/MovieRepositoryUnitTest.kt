package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.utils.MainCoroutineRule
import com.swvl.moviesdmb.infrastructure.dto.MovieResponse
import com.swvl.moviesdmb.models.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

private const val DATA_ERROR = "Unsupported field"

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryUnitTest {
    // Class under test
    private lateinit var moviesRepository: MoviesRepository
    private val dmbWebService = Mockito.mock(DmbWebService::class.java)


    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createRepository() {
        moviesRepository = MoviesRepository(dmbWebService)
    }

    @Test
    fun getMovies_requestsAllMoviesFromRemoteDataSource_thenReturnSelectedMovies() =
        mainCoroutineRule.runBlockingTest {
            //Given
            val movies = listOf(
                Movie(
                    "1",
                    "overview1",
                    "date1",
                    "poster1",
                    "path1",
                    "orgTitle1",
                    "title1", 0.1, "en"
                ),
                Movie(
                    "2",
                    "overview2",
                    "date2",
                    "poster2",
                    "path2",
                    "orgTitle2",
                    "title2", 0.2, "ar"
                )
            )

            Mockito.`when`(
                dmbWebService.popularMovies(anyInt())
            ).thenReturn(
                MovieResponse(movies.subList(0, 1))
            )

            //When (Action) GetAll By Page Number
            val actualMovies = moviesRepository.getAllById(1)

            // Then
            Assert.assertThat(actualMovies.size, IsEqual(1))
            Assert.assertThat(actualMovies[0].originalTitle, IsEqual("orgTitle1"))
            Assert.assertThat(actualMovies[0].overview, IsEqual("overview1"))
        }

    @Test
    fun getMovies_requestsAllMoviesFromRemoteDataSource_thenReturnEmpty() =
        mainCoroutineRule.runBlockingTest {
            //Given
            Mockito.`when`(
                dmbWebService.popularMovies(anyInt())
            ).thenReturn(
                MovieResponse(emptyList())
            )

            //When (Action) GetAll By Page Number
            val actualMovies = moviesRepository.getAllById(500)

            // Then
            Assert.assertThat(actualMovies.size, IsEqual(0))
        }

    @Test(expected = RuntimeException::class)
    fun getMovies_requestsAllMoviesFromRemoteDataSource_thenReturnThrowable() =
        mainCoroutineRule.runBlockingTest {
            //Given
            Mockito.`when`(
                dmbWebService.popularMovies(anyInt())
            ).thenThrow(
                RuntimeException(DATA_ERROR)
            )
            //When (Action) GetAll By Page Number
            moviesRepository.getAllById(-1)
        }
}