package com.swvl.moviesdmb.infrastructure

class TrailersRepository(private val apiService: DmbWebService) {
    suspend fun getTrailersOfMovie(movieId:String) = apiService.trailers(movieId).results
}