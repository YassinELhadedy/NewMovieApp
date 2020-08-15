package com.swvl.moviesdmb.infrastructure

import androidx.paging.PagingData
import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.Pagination
import com.swvl.moviesdmb.models.Trailer
import kotlinx.coroutines.flow.Flow

class TrailersRepository(private val apiService: DmbWebService) : GetAllRepository<Trailer> {

    override suspend fun getAll(pagination: Pagination): List<Trailer> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<Trailer> =
        apiService.trailers(id.toString()).results
}