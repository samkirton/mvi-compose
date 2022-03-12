package com.subasm.nfgen.domain.nft

import com.subasm.nfgen.data.room.dao.AccountDao
import com.subasm.nfgen.data.xrpl.rpc.nft.AccountNftsRequestParams
import com.subasm.nfgen.data.xrpl.rpc.nft.NFTClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import org.xrpl.xrpl4j.model.transactions.Address
import javax.inject.Inject

class NFTRepository @Inject constructor(
    private val nftClient: NFTClient,
    private val accountDao: AccountDao
) {

    fun getAccountNFTs(): Flow<NFTItems> {
        return accountDao.getSelectAccount().flatMapMerge { selectedAccount ->
            nftClient.getAccountNFTs(
                AccountNftsRequestParams(
                    Address.of(selectedAccount.first().address)
                )
            ).map { accountNftsResult ->
                NFTItems(
                    accountNftsResult.account_nfts.map {
                        NFTItem(it.TokenID, it.URI ?: "")
                    }
                )
            }
        }
    }
}
