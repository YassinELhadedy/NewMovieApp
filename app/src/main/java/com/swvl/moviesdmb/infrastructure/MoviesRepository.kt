package com.swvl.moviesdmb.infrastructure

class MoviesRepository(private val apiService: DmbWebService) {
    suspend fun getPopularMovieList() = apiService.popularMovies(1).results
}