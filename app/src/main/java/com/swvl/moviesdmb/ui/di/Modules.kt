package com.swvl.moviesdmb.ui.di

import android.content.Context
import com.swvl.moviesdmb.infrastructure.*
import com.swvl.moviesdmb.infrastructure.dao.MovieDao
import com.swvl.moviesdmb.models.service.DetailMovieService
import com.swvl.moviesdmb.ui.moviedetail.MovieDetailViewModel
import com.swvl.moviesdmb.ui.movielist.PopularMovieListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {


    fun setupRoomDao(context: Context): MovieDao = DatabaseBuilder.getInstance(context).movieDao()


    single { setupRoomDao(androidContext()) }
    single { OkHttpClientProvider().provideOkHttpClient }
    single { RetrofitFactory(get()).apiService }

    // Repositories
    single { ReviewsRepository(get()) }
    single { MoviesRemoteRepository(get(),get()) }
    single { TrailersRepository(get()) }
    single { CastRepository(get()) }
    single { MoviesLocalRepository(get()) }
    single { MovieProxyRepository(get(), get()) }

    // Service
    single { DetailMovieService(get(), get(), get()) }


    // View Models
    viewModel { PopularMovieListViewModel(MovieProxyRepository(get(),get())) }
    viewModel { MovieDetailViewModel(get()) }

}