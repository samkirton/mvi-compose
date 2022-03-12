package com.subasm.nfgen.data.xrpl.rpc.nft

import com.subasm.nfgen.data.xrpl.rpc.JsonRpc
import com.subasm.nfgen.data.xrpl.rpc.JsonRpcClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NFTClient @Inject constructor(
    private val jsonRpcClient: JsonRpcClient
) {

    fun getAccountNFTs(request: AccountNftsRequestParams): Flow<AccountNftsResult> {
        return jsonRpcClient.post(
            JsonRpc(
                "account_nfts",
                listOf(request)
            ),
            AccountNftsResult::class.java
        )
    }
}
