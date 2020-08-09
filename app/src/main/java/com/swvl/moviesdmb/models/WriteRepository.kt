package com.swvl.moviesdmb.models

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty


/**
 * Write Repository with Write Only Methods
 */
interface WriteRepository<in T, out U> : PostRepository<T, U>,
    PutRepository<T>, DeleteRepository {

    /* A naive implementation for insertOrUpdate that should
     * be enhanced in subclasses
     */
    @ExperimentalCoroutinesApi
    fun insertOrUpdate(entity: T): Flow<U> =
        insert(entity)
            .map { it }
            .onEmpty {
                (update(entity).map { _ ->
                    @Suppress("UNCHECKED_CAST")
                    entity as U
                })
            }
}