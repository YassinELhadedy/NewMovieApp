package com.swvl.moviesdmb.infrastructure

import androidx.paging.PagingSource
import com.swvl.moviesdmb.models.Movie
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

class MoviePagingSource(
        private val service: DmbWebService
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val response = service.popularMovies(position)
            val repos = response.results.map { it.toMovie() }
            LoadResult.Page(
                    data = repos,
                    prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
