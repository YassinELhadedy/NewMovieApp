package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.Pagination
import com.swvl.moviesdmb.models.Review

class ReviewsRepository(private val apiService: DmbWebService) : GetAllRepository<Review> {
    override suspend fun getAll(pagination: Pagination): List<Review> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<Review> = apiService.reviews(id.toString()).results
}