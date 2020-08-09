package com.swvl.moviesdmb.models.service

import com.swvl.moviesdmb.infrastructure.CastRepository
import com.swvl.moviesdmb.infrastructure.ReviewsRepository
import com.swvl.moviesdmb.infrastructure.TrailersRepository
import com.swvl.moviesdmb.ui.moviedetail.adapter.DetailItemViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class DetailMovieService(
    private val trailersRepository: TrailersRepository,
    private val reviewsRepository: ReviewsRepository,
    private val castRepository: CastRepository
) {
    //FIXME : As per DDD Arch should this be application service not Domain Service .But domain service should or orchestration between domain models. and
    //FIXME: domain models shouldn't be anemic
    private lateinit var item: DetailItemViewModel

    suspend fun getDetailMovie(movieId: String): DetailItemViewModel {
        coroutineScope {
            val trailersDeferred = async { trailersRepository.getAllById(movieId.toInt()) }
            val reviewsDeferred = async { reviewsRepository.getAllById(movieId.toInt()) }
            val castsDeferred = async { castRepository.getCastsOfMovie(movieId.toInt()) }
            item = DetailItemViewModel(
                castsDeferred.await(),
                reviewsDeferred.await(),
                trailersDeferred.await()
            )
        }
        return item
    }
}