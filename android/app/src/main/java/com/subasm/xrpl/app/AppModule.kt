package com.subasm.xrpl.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.subasm.xrpl.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun context(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun sharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.app_shared_preferences_package),
            Context.MODE_PRIVATE
        )
    }
}
