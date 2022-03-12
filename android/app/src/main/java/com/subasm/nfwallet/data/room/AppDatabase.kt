package com.subasm.nfwallet.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.subasm.nfwallet.data.room.dao.AccountDao
import com.subasm.nfwallet.data.room.model.AccountEntity

@Database(
    entities = [
        AccountEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
