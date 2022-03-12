package com.subasm.nfgen.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.subasm.nfgen.data.room.dao.AccountDao
import com.subasm.nfgen.data.room.model.AccountEntity

@Database(
    entities = [
        AccountEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
