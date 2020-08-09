package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.models.Pagination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

/* TODO : As we know we have 3 ways to handle dependencies of classUnderTest in Unit test and allow test isolation 1- Stubs 2- Mocking 3- faking. but i'd prefer Mocking way since it's so powerfull framework
to see the differences please see video https://www.youtube.com/watch?v=qFaBHHg6RQU
* */
@ExperimentalCoroutinesApi
class FakeTestMovieRepository : GetAllRepository<Movie> {
    val testDispatcher = TestCoroutineDispatcher()

    private var shouldReturnError = false

    private val observableMovies = listOf(
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

    override suspend fun getAll(pagination: Pagination): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<Movie> = observableMovies
}