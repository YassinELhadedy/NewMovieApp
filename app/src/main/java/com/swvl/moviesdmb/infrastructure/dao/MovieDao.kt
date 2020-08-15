package com.swvl.moviesdmb.infrastructure.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.swvl.moviesdmb.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE pageId = :pageId")
    suspend fun getAll(pageId: Long): List<Movie>

    @Insert
    suspend fun insertAll(users: List<Movie>)

    @Delete
    suspend fun delete(user: Movie)
}