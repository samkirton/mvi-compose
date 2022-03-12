package com.subasm.nfgen.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.subasm.nfgen.data.room.model.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert
    fun insert(account: AccountEntity)

    @Query("SELECT DISTINCT uid, address, balance, selected FROM Account ORDER BY uid ASC LIMIT 0, 100")
    fun getAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT DISTINCT uid, address, balance, selected FROM Account WHERE selected = 1 ORDER BY uid ASC LIMIT 0, 1")
    fun getSelectAccount(): Flow<List<AccountEntity>>

    @Query("UPDATE Account SET selected = 0")
    fun deselectAccounts()
}
