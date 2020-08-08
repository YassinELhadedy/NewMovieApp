package com.swvl.moviesdmb.ui.app

import android.app.Application
import com.swvl.moviesdmb.ui.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class MovieDMB : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            modules(listOf(applicationModule))
            androidContext(this@MovieDMB)
        }
    }
}