package com.swvl.moviesdmb.ui.localmovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.LocalMovie
import com.swvl.moviesdmb.ui.localmovies.adapter.LocalMovieItemViewModel
import com.swvl.moviesdmb.ui.localmovies.adapter.LocalMovieItemViewModel.Companion.toLocalMovieItemViewModel
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.coroutines.launch

class LocalMovieListViewModel(private val localMovieRepository: GetAllRepository<LocalMovie>) :
    ViewModel() {
    val moviesItem = MutableLiveData<Resource<List<LocalMovieItemViewModel>>>()
    var items: List<LocalMovieItemViewModel>? = null

    init {
        getLocalMovieList()
    }

    private fun getLocalMovieList() = viewModelScope.launch {
        moviesItem.postValue(Resource.loading(data = null))
        kotlin.runCatching {
            moviesItem.postValue(
                Resource.success(data = localMovieRepository.getAllById(1)
                    .map { movie ->
                        movie.toLocalMovieItemViewModel()
                    })
            )
        }.getOrElse { ex ->
            moviesItem.postValue(
                Resource.error(
                    data = null,
                    message = ex.message ?: "Error Occurred!"
                )
            )
        }
    }
}