package com.swvl.moviesdmb.infrastructure

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swvl.moviesdmb.infrastructure.dao.MovieDao
import com.swvl.moviesdmb.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}