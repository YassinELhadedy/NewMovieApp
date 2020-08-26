package com.swvl.moviesdmb.ui.localmovies

import com.swvl.moviesdmb.models.LocalMovie

data class LocalMovieItemViewModel(
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
){
    companion object {
        fun LocalMovie.toLocalMovieItemViewModel() = LocalMovieItemViewModel(
           title, year, cast, genres, rating
        )
    }

    fun toLocalMovie() = LocalMovie(
        title, year, cast, genres, rating
    )
}
