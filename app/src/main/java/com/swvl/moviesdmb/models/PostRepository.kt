package com.swvl.moviesdmb.models

/**
 * PostRepository
 */
interface PostRepository<in T, out U> {
    suspend fun insert(entity: T): Unit
}