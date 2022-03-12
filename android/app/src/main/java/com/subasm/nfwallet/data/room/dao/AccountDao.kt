package com.subasm.nfwallet.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.subasm.nfwallet.data.room.model.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert
    fun insert(account: AccountEntity)

    @Query("SELECT DISTINCT uid, address, balance, selected FROM Account ORDER BY uid ASC LIMIT 0, 100")
    fun getAccounts(): Flow<List<AccountEntity>>
}
