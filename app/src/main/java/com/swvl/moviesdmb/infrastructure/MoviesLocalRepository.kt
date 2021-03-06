package com.swvl.moviesdmb.infrastructure

import androidx.paging.PagingData
import com.swvl.moviesdmb.infrastructure.dao.MovieDao
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.models.Pagination
import com.swvl.moviesdmb.models.Repository
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow


class MoviesLocalRepository(private val movieDao: MovieDao) : Repository<Movie, Movie> {

    override suspend fun get(id: Int): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(pagination: Pagination): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllById(id: Int): List<Movie> = movieDao.getAll(id.toLong())

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(entity: Movie): Unit {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: Movie): Observable<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(entity: List<Movie>): Unit = movieDao.insertAll(entity)
}