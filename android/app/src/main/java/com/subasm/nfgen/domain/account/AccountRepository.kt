package com.subasm.nfgen.domain.account

import com.subasm.nfgen.data.room.dao.AccountDao
import com.subasm.nfgen.data.xrpl.rpc.account.AccountClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams
import org.xrpl.xrpl4j.model.transactions.Address
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao,
    private val accountClient: AccountClient
) {

    fun getAccountBalance(): Flow<AccountBalance> {
        return accountDao.getSelectAccount().flatMapMerge { selectedAccount ->
            accountClient.getAccountInfo(
                AccountInfoRequestParams.of(Address.of(selectedAccount.first().address))
            ).map {
                AccountBalance(
                    balance = it.accountData().balance().toString(),
                    previousTx = it.accountData().previousTransactionId().toString()
                )
            }
        }
    }
}
