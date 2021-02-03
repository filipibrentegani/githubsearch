package com.filipibrentegani.githubsearch.application

import android.app.Application
import com.filipibrentegani.githubsearch.featuretoggle.FeatureToggleHelper

class SearchApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FeatureToggleHelper.initialize(this)
    }
}