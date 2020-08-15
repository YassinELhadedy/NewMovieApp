package com.swvl.moviesdmb.infrastructure

import androidx.paging.PagingData
import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.models.Pagination
import com.swvl.moviesdmb.ui.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.Flow

class MoviesRemoteRepository(
    private val apiService: DmbWebService,
    private val moviesLocalRepository: MoviesLocalRepository
) : GetAllRepository<Movie> {


    override suspend fun getAll(pagination: Pagination): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<Movie> = wrapEspressoIdlingResource {
        apiService.popularMovies(id).results.map {
            it.toMovie().copy(pageId = id.toLong())
        }.also { movies: List<Movie> ->
            moviesLocalRepository.insertAll(
                movies
            )
        }
    }
}