package com.swvl.moviesdmb.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * GetAllRepository
 */
interface GetAllRepository<out T> {
    suspend fun getAll(pagination: Pagination): List<T>
    suspend fun getAllById(id: Int): List<T>
}