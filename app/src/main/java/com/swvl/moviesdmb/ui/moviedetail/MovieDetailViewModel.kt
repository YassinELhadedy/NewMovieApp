package com.swvl.moviesdmb.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.swvl.moviesdmb.models.service.DetailMovieService
import com.swvl.moviesdmb.ui.moviedetail.adapter.DetailItemViewModel
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(
    private val detailMovieService: DetailMovieService
) : ViewModel() {

    fun getMovieDetail(movieId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = DetailItemViewModel(detailMovieService.getDetailMovie(movieId).first,detailMovieService.getDetailMovie(movieId).second,
                        detailMovieService.getDetailMovie(movieId).third)
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}