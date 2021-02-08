package com.filipibrentegani.githubsearch.application

import android.app.Application
import com.filipibrentegani.githubsearch.featuretoggle.FeatureToggleHelper
import com.filipibrentegani.githubsearch.findrepositoriesscreen.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SearchApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FeatureToggleHelper.initialize(this)

        startKoin {
            androidLogger()
            androidContext(this@SearchApplication)
            modules(module)
        }
    }
}