package com.swvl.moviesdmb.models.service

import com.swvl.moviesdmb.infrastructure.CastRepository
import com.swvl.moviesdmb.infrastructure.ReviewsRepository
import com.swvl.moviesdmb.infrastructure.TrailersRepository
import com.swvl.moviesdmb.models.Cast
import com.swvl.moviesdmb.models.Review
import com.swvl.moviesdmb.models.Trailer
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class DetailMovieService(
    private val trailersRepository: TrailersRepository,
    private val reviewsRepository: ReviewsRepository,
    private val castRepository: CastRepository
) {
    //FIXME : As per DDD Arch should this be application service not Domain Service .But domain service should or orchestration between domain models. and
    //FIXME: domain models shouldn't be anemic
    private lateinit var item: Triple<List<Cast>, List<Review>, List<Trailer>>

    suspend fun getDetailMovie(movieId: String): Triple<List<Cast>, List<Review>, List<Trailer>> {
        coroutineScope {
            val trailersDeferred = async { trailersRepository.getAllById(movieId.toInt()) }
            val reviewsDeferred = async { reviewsRepository.getAllById(movieId.toInt()) }
            val castsDeferred = async { castRepository.getCastsOfMovie(movieId.toInt()) }
            item = Triple(
                castsDeferred.await(),
                reviewsDeferred.await(),
                trailersDeferred.await()
            )
        }
        return item
    }
}