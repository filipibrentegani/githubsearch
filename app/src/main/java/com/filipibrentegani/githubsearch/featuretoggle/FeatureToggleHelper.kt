package com.filipibrentegani.githubsearch.featuretoggle

import android.content.Context
import com.filipibrentegani.githubsearch.BuildConfig
import com.filipibrentegani.githubsearch.R
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

object FeatureToggleHelper {

    const val FORCE_UPDATE_MIN_VERSION = "forceUpdateMinVersion"

    fun initialize(context: Context) {
        FirebaseApp.initializeApp(context)
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.fetch(if (BuildConfig.DEBUG) 0 else 3600).addOnCompleteListener {
            if (it.isSuccessful) {
                remoteConfig.activate()
            }
        }
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    fun getString(key: RemoteConfigKeys): String {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        return remoteConfig.getString(key.key)
    }
}