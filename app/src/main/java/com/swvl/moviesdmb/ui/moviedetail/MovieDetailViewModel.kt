package com.swvl.moviesdmb.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.swvl.moviesdmb.models.service.DetailMovieService
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(
    private val detailMovieService: DetailMovieService
) : ViewModel() {
    private lateinit var item: DetailItemViewModel


    fun getMovieDetail(movieId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = detailMovieService.getDetailMovie(movieId)
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}