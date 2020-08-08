package com.swvl.moviesdmb.infrastructure

class CastRepository(private val apiService: DmbWebService) {
    suspend fun getCastsOfMovie(movieId: Int) = apiService.getCasts(movieId).cast
}