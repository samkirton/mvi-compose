package com.subasm.nfgen.data.room

import android.app.Application
import androidx.room.Room
import com.subasm.nfgen.R
import com.subasm.nfgen.data.room.dao.AccountDao
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
