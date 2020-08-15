package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.models.Movie


interface DatabaseHelper {

    suspend fun getMovies(): List<Movie>

    suspend fun insertAll(users: List<Movie>)
}