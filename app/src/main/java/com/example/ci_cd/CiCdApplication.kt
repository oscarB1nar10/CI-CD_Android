package com.example.ci_cd

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class CiCdApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCenter.start(
            this, BuildConfig.APP_CENTER_KEY,
            Analytics::class.java, Crashes::class.java
        )
    }
}