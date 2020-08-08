package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory(private val okHttpClient: OkHttpClient) {
    val apiService: DmbWebService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(DmbWebService::class.java)
    }
}