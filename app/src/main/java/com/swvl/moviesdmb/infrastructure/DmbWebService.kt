package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.infrastructure.dto.CastResponses
import com.swvl.moviesdmb.infrastructure.dto.MovieResponse
import com.swvl.moviesdmb.infrastructure.dto.ReviewResponse
import com.swvl.moviesdmb.infrastructure.dto.TrailersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DmbWebService {
    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun popularMovies(@Query("page") page: Int): MovieResponse

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    suspend fun highestRatedMovies(@Query("page") page: Int): MovieResponse

    @GET("3/discover/movie?language=en&sort_by=release_date.desc")
    suspend fun newestMovies(
        @Query("release_date.lte") maxReleaseDate: String?,
        @Query("vote_count.gte") minVoteCount: Int
    ): MovieResponse

    @GET("3/movie/{id}/casts")
    suspend fun getCasts(
        @Path("id") id: Int): CastResponses

    @GET("3/movie/{movieId}/videos")
    suspend fun trailers(@Path("movieId") movieId: String): TrailersResponse

    @GET("3/movie/{movieId}/reviews")
    suspend fun reviews(@Path("movieId") movieId: String): ReviewResponse

    @GET("3/search/movie?language=en-US&page=1")
    suspend fun searchMovies(@Query("query") searchQuery: String): MovieResponse
}