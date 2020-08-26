package com.swvl.moviesdmb.models

/**
 * GetAllRepository
 */
interface GetAllRepository<out T> {
    suspend fun getAll(pagination: Pagination): List<T>
    suspend fun getAllById(id: Int): List<T>
}