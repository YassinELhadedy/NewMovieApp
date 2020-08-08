package com.swvl.moviesdmb.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.swvl.moviesdmb.infrastructure.MoviesRepository
import com.swvl.moviesdmb.ui.movielist.adapter.MovieItemViewModel.Companion.toMovieItemViewModel
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.coroutines.Dispatchers

class PopularMovieListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getPopularMovieList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = moviesRepository.getPopularMovieList()
                        .map { movie -> movie.toMovieItemViewModel() }
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}