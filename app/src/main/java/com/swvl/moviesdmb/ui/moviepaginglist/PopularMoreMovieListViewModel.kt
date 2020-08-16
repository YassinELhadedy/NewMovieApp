package com.swvl.moviesdmb.ui.moviepaginglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.swvl.moviesdmb.infrastructure.MoviePaginateeRepository
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.utils.Event
import kotlinx.coroutines.flow.Flow

class PopularMoreMovieListViewModel(private val moviePaginateeRepository: MoviePaginateeRepository) :
    ViewModel() {

    val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _openMovieEvent = MutableLiveData<Event<Movie>>()
    val openMovieEvent: LiveData<Event<Movie>> = _openMovieEvent

    var items: PagingData<Movie>? = null
    val dataRefresh = MutableLiveData<Boolean>()


    fun getPopularMovieList(): Flow<PagingData<Movie>> {
        _dataLoading.value = true
        return moviePaginateeRepository.getAll().cachedIn(viewModelScope)
    }

    /**
     * Called by Data Binding.
     */
    fun openMovie(movie: Movie) {
        _openMovieEvent.value = Event(movie)
    }

    fun refresh() {
        dataRefresh.postValue(true)
    }
}