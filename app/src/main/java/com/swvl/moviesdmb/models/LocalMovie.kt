package com.swvl.moviesdmb.models

data class LocalMovie(
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
)
