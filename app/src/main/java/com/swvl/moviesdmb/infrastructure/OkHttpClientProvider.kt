package com.swvl.moviesdmb.infrastructure

import com.swvl.moviesdmb.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpClientProvider {

    val provideOkHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(
            CONNECT_TIMEOUT_IN_MS.toLong(),
            TimeUnit.MILLISECONDS
        ).addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }
        builder.build()
    }

    companion object {
        private const val CONNECT_TIMEOUT_IN_MS = 30000
    }
}