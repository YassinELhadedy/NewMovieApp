package com.swvl.moviesdmb.models

/**
 * GetRepository
 */
interface GetRepository<out T> {
    suspend fun get(id: Int): T
}