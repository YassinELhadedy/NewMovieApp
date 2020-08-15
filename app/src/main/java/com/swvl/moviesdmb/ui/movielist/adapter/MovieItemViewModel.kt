package com.swvl.moviesdmb.ui.movielist.adapter

import com.swvl.moviesdmb.models.Movie

data class MovieItemViewModel(
    val id: String, val overview: String, val releaseDate: String,
    val posterPath: String, val backdropPath: String?,
    val originalTitle: String, val title: String,
    val voteAverage: String
) {
    companion object {
        fun Movie.toMovieItemViewModel(): MovieItemViewModel = MovieItemViewModel(
            id,
            overview,
            releaseDate,
            posterPath,
            backdropPath,
            originalTitle,
            title,
            voteAverage.toString()
        )
    }

    fun toMovie() = Movie(
        null,
        id,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalTitle,
        title,
        voteAverage.toDouble(), null
    )
}