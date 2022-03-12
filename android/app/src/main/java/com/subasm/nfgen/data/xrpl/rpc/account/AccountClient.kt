package com.subasm.nfgen.data.xrpl.rpc.account

import com.subasm.nfgen.data.xrpl.rpc.FaucetClient
import com.subasm.nfgen.data.xrpl.rpc.FaucetResult
import com.subasm.nfgen.data.xrpl.rpc.JsonRpc
import com.subasm.nfgen.data.xrpl.rpc.JsonRpcClient
import kotlinx.coroutines.flow.Flow
import org.xrpl.xrpl4j.model.client.XrplMethods
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoResult
import javax.inject.Inject

class AccountClient @Inject constructor(
    private val jsonRpcClient: JsonRpcClient,
    private val faucetClient: FaucetClient
) {

    fun getAccountInfo(request: AccountInfoRequestParams): Flow<AccountInfoResult> {
        return jsonRpcClient.post(
            JsonRpc(
                XrplMethods.ACCOUNT_INFO,
                listOf(request)
            ),
            AccountInfoResult::class.java
        )
    }

    fun createAccount(): Flow<FaucetResult> = faucetClient.post()
}
