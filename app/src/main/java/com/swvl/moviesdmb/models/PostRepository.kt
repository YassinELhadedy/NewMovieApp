package com.swvl.moviesdmb.models

import kotlinx.coroutines.flow.Flow

/**
 * PostRepository
 */
interface PostRepository<in T, out U> {
    fun insert(entity: T): Flow<U>
}