package com.subasm.nfwallet.data.room

import android.app.Application
import androidx.room.Room
import com.subasm.nfwallet.R
import com.subasm.nfwallet.data.room.dao.AccountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun appDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            application.getString(R.string.app_database_name)
        ).build()
    }

    @Provides
    @Singleton
    fun accountDao(appDatabase: AppDatabase): AccountDao =
        appDatabase.accountDao()
}
