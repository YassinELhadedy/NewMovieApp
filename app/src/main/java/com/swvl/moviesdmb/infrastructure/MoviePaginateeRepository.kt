package com.swvl.moviesdmb.infrastructure

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.swvl.moviesdmb.models.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that works with local and remote data sources.
 */
class MoviePaginateeRepository(
    private val service: DmbWebService
)  {

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

      fun getAll(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(service) }
        ).flow
    }

}
