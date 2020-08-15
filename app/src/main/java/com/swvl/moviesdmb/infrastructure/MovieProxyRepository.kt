package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.models.AbstractProxyGetRepository
import com.swvl.moviesdmb.models.Movie

/**
 * MovieProxyRepository
 */
class MovieProxyRepository(
    moviesLocalRepository: MoviesLocalRepository,
    moviesRemoteRepository: MoviesRemoteRepository
) :
    AbstractProxyGetRepository<Movie, Movie>(
        moviesLocalRepository,
        moviesRemoteRepository
    )