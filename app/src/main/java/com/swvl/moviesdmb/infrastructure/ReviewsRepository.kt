package com.swvl.moviesdmb.infrastructure

class ReviewsRepository(private val apiService: DmbWebService) {
    suspend fun getReviewsOfMovie(movieId:String) = apiService.reviews(movieId).results
}