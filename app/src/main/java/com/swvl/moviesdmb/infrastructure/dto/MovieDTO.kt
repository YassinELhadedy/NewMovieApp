package com.swvl.moviesdmb.infrastructure.dto

import com.google.gson.annotations.SerializedName
import com.swvl.moviesdmb.models.Movie

class MovieDTO(
    val id: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("original_title") val originalTitle: String,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("original_language") val originalLanguage: String?
) {

    companion object {
        fun Movie.toMovieDTO(): MovieDTO = MovieDTO(
            id.toString(),
            overview,
            releaseDate,
            posterPath,
            backdropPath,
            originalTitle,
            title,
            voteAverage, originalLanguage
        )
    }

    fun toMovie(): Movie = Movie(
        id.toInt(),
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalTitle,
        title,
        voteAverage, originalLanguage
    )
}