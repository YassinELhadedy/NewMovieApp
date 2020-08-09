package com.swvl.moviesdmb.models

/**
 * AbstractProxyGetRepository
 */
//FIXME : uncomment this class if we need to handle communication between different data sources 1- cash DS 2- remote DS
/*
abstract class AbstractProxyGetRepository<out T, U>(private val cacheRepository: Repository<T, U>,
                                                    private val getRepository: GetRepository<U>) :
        GetRepository<U> {

    protected abstract fun convert(entity: U): T

    override fun get(id: Int): Observable<out U> {
        val cached = cacheRepository.get(id).cache()
        val service by lazy {
            getRepository.get(id).flatMap { entity: U ->
                cacheRepository.insertOrUpdate(convert(entity))
                        .map { _ -> entity }
                        .onErrorReturn { _ -> entity }
            }.cache()
        }
        return cached.isEmpty.toObservable()
                .flatMap {
                    if (it) {
                        service
                    } else {
                        cached
                    }
                }
                .onErrorResumeNext { _: Throwable ->
                    service
                }
    }
}
 */