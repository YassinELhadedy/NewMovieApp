package com.swvl.moviesdmb.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.movielist.adapter.MovieItemViewModel
import com.swvl.moviesdmb.ui.movielist.adapter.MovieItemViewModel.Companion.toMovieItemViewModel
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.coroutines.launch

class PopularMovieListViewModel(private val moviesRepository: GetAllRepository<Movie>) :
    ViewModel() {

    val moviesItem = MutableLiveData<Resource<List<MovieItemViewModel>>>()
    var items: Resource<List<MovieItemViewModel>>? = null

    init {
        getPopularMovieList()
    }

    private fun getPopularMovieList() = viewModelScope.launch {
        moviesItem.postValue(Resource.loading(data = null))
        try {
            moviesItem.postValue(
                Resource.success(
                    data = moviesRepository.getAllById(1)
                        .map { movie -> movie.toMovieItemViewModel() }
                )
            )
        } catch (exception: Exception) {
            moviesItem.postValue(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }
}