package com.work.motionanalysiscamera

import android.app.Application
import android.content.Context
import com.work.motionanalysiscamera.di.networkModule
import com.work.motionanalysiscamera.di.presenterModule
import com.work.motionanalysiscamera.di.repositoryModule
import com.work.motionanalysiscamera.di.sourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    sourceModule,
                    repositoryModule,
                    networkModule,
                    presenterModule
                )
            )
        }
    }

    fun context(): Context = applicationContext

    companion object {
        lateinit var instance: App
            private set
    }

}