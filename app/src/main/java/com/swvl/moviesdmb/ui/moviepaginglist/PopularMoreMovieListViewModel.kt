package com.swvl.moviesdmb.ui.moviepaginglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.swvl.moviesdmb.infrastructure.MoviePaginateeRepository

class PopularMoreMovieListViewModel(private val moviePaginateeRepository: MoviePaginateeRepository) :
    ViewModel() {


    fun getPopularMovieList() = moviePaginateeRepository.getAll().cachedIn(viewModelScope)

}