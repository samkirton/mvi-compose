package com.subasm.nfwallet.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
abstract class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
