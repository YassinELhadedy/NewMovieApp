package com.swvl.moviesdmb.ui.localmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swvl.moviesdmb.models.GetAllRepository
import com.swvl.moviesdmb.models.LocalMovie
import com.swvl.moviesdmb.ui.localmovies.LocalMovieItemViewModel.Companion.toLocalMovieItemViewModel
import com.swvl.moviesdmb.ui.utils.Resource
import kotlinx.coroutines.launch

class LocalMovieListViewModel(private val localMovieRepository: GetAllRepository<LocalMovie>) :
    ViewModel() {
    val moviesItem = MutableLiveData<Resource<List<LocalMovieItemViewModel>>>()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private fun getLocalMovieList() = viewModelScope.launch {
        moviesItem.postValue(Resource.loading(data = null))
        _dataLoading.value = true
        try {
            moviesItem.postValue(
                Resource.success(data = localMovieRepository.getAllById(1)
                    .map { movie ->
                        _dataLoading.value = false
                        movie.toLocalMovieItemViewModel()
                    })
            )
        } catch (exception: Exception) {
            _dataLoading.value = false
            moviesItem.postValue(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!"
                )
            )
        }
    }
}