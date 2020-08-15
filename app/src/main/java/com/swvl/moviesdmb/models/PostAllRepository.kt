package com.swvl.moviesdmb.models

import kotlinx.coroutines.flow.Flow

/**
 * PostRepository
 */
interface PostAllRepository<in T, out U> {
    suspend fun insertAll(entity: List<T>): Unit
}