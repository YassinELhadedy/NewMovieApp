package com.swvl.moviesdmb.ui.di

import com.swvl.moviesdmb.infrastructure.*
import com.swvl.moviesdmb.models.service.DetailMovieService
import com.swvl.moviesdmb.ui.moviedetail.MovieDetailViewModel
import com.swvl.moviesdmb.ui.movielist.PopularMovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    single { OkHttpClientProvider().provideOkHttpClient }
    single { RetrofitFactory(get()).apiService }

    // Repositories
    single { ReviewsRepository(get()) }
    single { MoviesRepository(get()) }
    single { TrailersRepository(get()) }
    single { CastRepository(get()) }

    // Service
    single { DetailMovieService(get(), get(), get()) }


    // View Models
    viewModel { PopularMovieListViewModel(MoviesRepository(get())) }
    viewModel { MovieDetailViewModel(get()) }

}