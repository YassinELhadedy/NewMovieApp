package com.swvl.moviesdmb.models.service

import com.swvl.moviesdmb.infrastructure.CastRepository
import com.swvl.moviesdmb.infrastructure.ReviewsRepository
import com.swvl.moviesdmb.infrastructure.TrailersRepository
import com.swvl.moviesdmb.ui.moviedetail.DetailItemViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class DetailMovieService(
    private val trailersRepository: TrailersRepository,
    private val reviewsRepository: ReviewsRepository,
    private val castRepository: CastRepository
) {
    private lateinit var item: DetailItemViewModel

    suspend fun getDetailMovie(movieId: String): DetailItemViewModel {
        coroutineScope {
            val trailersDeferred = async { trailersRepository.getTrailersOfMovie(movieId) }
            val reviewsDeferred = async { reviewsRepository.getReviewsOfMovie(movieId) }
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