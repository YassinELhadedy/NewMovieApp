package com.swvl.moviesdmb.ui.di

import android.content.Context
import com.swvl.moviesdmb.infrastructure.*
import com.swvl.moviesdmb.infrastructure.dao.MovieDao
import com.swvl.moviesdmb.models.service.DetailMovieService
import com.swvl.moviesdmb.ui.localmovies.LocalMovieListViewModel
import com.swvl.moviesdmb.ui.moviedetail.MovieDetailViewModel
import com.swvl.moviesdmb.ui.movielist.PopularMovieListViewModel
import com.swvl.moviesdmb.ui.moviepaginglist.PopularMoreMovieListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {


    fun setupRoomDB(context: Context) = DatabaseBuilder.getInstance(context)
    fun setupRoomDao(context: Context): MovieDao = setupRoomDB(context).movieDao()
    fun setupInputStream(context: Context) = context.assets.open("LocalMovies.json")

    single { setupRoomDB(androidContext()) }
    single { setupRoomDao(androidContext()) }
    single { OkHttpClientProvider().provideOkHttpClient }
    single { RetrofitFactory(get()).apiService }
//    single { setupInputStream(androidContext()) } //FIXME: must'nt be singleton in order to support rotation. if its singleton then fetch once and then close transactions. and throw (org.json.JSONException: End of input at character 0 of) for second fetch trial.

    // Repositories
    single { ReviewsRepository(get()) }
    single { MoviesRemoteRepository(get(), get()) }
    single { TrailersRepository(get()) }
    single { CastRepository(get()) }
    single { MoviesLocalRepository(get()) }
    single { MovieProxyRepository(get(), get()) }
    single { MoviePaginateeRepository(get()) }
    single { LocalMovieRepository(get()) }

    // Service
    single { DetailMovieService(get(), get(), get()) }


    // View Models
    viewModel { PopularMovieListViewModel(MovieProxyRepository(get(), get())) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { PopularMoreMovieListViewModel(get()) }
    viewModel { LocalMovieListViewModel(LocalMovieRepository(setupInputStream(androidContext()))) }
}