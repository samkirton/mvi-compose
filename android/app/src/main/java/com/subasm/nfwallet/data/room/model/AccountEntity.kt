package com.subasm.nfwallet.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")
data class AccountEntity(
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "balance") val balance: String,
    @ColumnInfo(name = "selected") val selected: Boolean,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0
)
