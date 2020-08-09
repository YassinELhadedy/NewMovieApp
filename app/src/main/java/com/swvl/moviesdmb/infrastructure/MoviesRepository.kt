package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.models.Pagination

class MoviesRepository(private val apiService: DmbWebService) : GetAllRepository<Movie> {

    override suspend fun getAll(pagination: Pagination): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<Movie> = apiService.popularMovies(1).results
}