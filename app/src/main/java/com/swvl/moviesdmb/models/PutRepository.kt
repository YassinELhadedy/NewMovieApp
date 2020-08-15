package com.swvl.moviesdmb.models

import io.reactivex.Observable

/**
 * PutRepository
 */
interface PutRepository<in T> {
   suspend fun update(entity: T): Observable<Unit>
}