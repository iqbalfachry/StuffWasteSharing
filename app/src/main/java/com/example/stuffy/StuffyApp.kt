package com.example.stuffy

import android.app.Application
import com.example.stuffy.di.useCaseModule
import com.example.stuffy.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class StuffyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.NONE)
            androidContext(this@StuffyApp)
            modules(
                listOf(
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}