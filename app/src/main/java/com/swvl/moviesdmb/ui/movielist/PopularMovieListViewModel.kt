package com.swvl.moviesdmb.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swvl.moviesdmb.models.AbstractProxyGetRepository
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.movielist.adapter.MovieItemViewModel
import com.swvl.moviesdmb.ui.movielist.adapter.MovieItemViewModel.Companion.toMovieItemViewModel
import com.swvl.moviesdmb.ui.utils.Event
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.coroutines.launch

class PopularMovieListViewModel(private val movieProxyRepository: AbstractProxyGetRepository<Movie, Movie>) :
    ViewModel() {
    var pageIndex = 1

    val moviesItem = MutableLiveData<Resource<List<MovieItemViewModel>>>()
    var items: List<MovieItemViewModel>? = null

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _openMovieEvent = MutableLiveData<Event<Movie>>()
    val openMovieEvent: LiveData<Event<Movie>> = _openMovieEvent

    init {
        getPopularMovieList()
    }

    private fun getPopularMovieList() = viewModelScope.launch {
        moviesItem.postValue(Resource.loading(data = null))
        _dataLoading.value = true
        kotlin.runCatching {
            moviesItem.postValue(
                Resource.success(data = movieProxyRepository.getAllById(pageIndex)
                    .map { movie ->
                        _dataLoading.value = false
                        movie.toMovieItemViewModel()
                    })
            )
        }.getOrElse { ex ->
            _dataLoading.value = false
            moviesItem.postValue(
                Resource.error(
                    data = null,
                    message = ex.message ?: "Error Occurred!"
                )
            )
        }
    }

    /**
     * Called by Data Binding.
     */
    fun openMovie(movie: Movie) {
        _openMovieEvent.value = Event(movie)
    }

    fun refresh() {
        getPopularMovieList()
    }
}