package com.swvl.moviesdmb.models

import io.reactivex.Observable

/**
 * PutRepository
 */
interface PutRepository<in T> {
    fun update(entity: T): Observable<Unit>
}