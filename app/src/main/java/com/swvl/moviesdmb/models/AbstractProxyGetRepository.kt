package com.swvl.moviesdmb.models

/**
 * AbstractProxyGetRepository
 */
//FIXME : uncomment this class if we need to handle communication between different data sources 1- cash DS 2- remote DS

abstract class AbstractProxyGetRepository<out T, U>(
    private val cacheRepository: Repository<T, U>,
    private val getAllRepository: GetAllRepository<U>
) :
    GetAllRepository<U> {

    override suspend fun getAll(pagination: Pagination): List<U> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<U> {
        val moviesFromDb = cacheRepository.getAllById(id)
        return if (moviesFromDb.isEmpty()) {
            val moviesFromApi = getAllRepository.getAllById(id)
            moviesFromApi
        } else {
            moviesFromDb
        }
    }
}
