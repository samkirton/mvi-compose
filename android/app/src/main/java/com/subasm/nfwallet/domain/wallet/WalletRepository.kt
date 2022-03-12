package com.subasm.nfwallet.domain.wallet

import com.subasm.nfwallet.data.keystore.SecureItem
import com.subasm.nfwallet.data.keystore.SecureKeyStore
import com.subasm.nfwallet.data.room.dao.AccountDao
import com.subasm.nfwallet.data.room.model.AccountEntity
import com.subasm.nfwallet.data.xrpl.GenerateWallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WalletRepository @Inject constructor(
    private val generateWallet: GenerateWallet,
    private val secureKeyStore: SecureKeyStore,
    private val accountDao: AccountDao
) {

    fun createAddress(): Flow<XRPLAddress> {
        return generateWallet.generate().map {
            val (address, seed) = it
            XRPLAddress(address.value(), seed)
        }.flatMapMerge { xrplAddress ->
            persistXrplAddress(xrplAddress).map {
                xrplAddress
            }
        }
    }

    fun importSeed(seedValue: String): Flow<Unit> {
        return generateWallet.fromSeed(seedValue).map {
            val (address, seed) = it
            XRPLAddress(address.value(), seed)
        }.flatMapMerge { xrplAddress ->
            persistXrplAddress(xrplAddress)
        }
    }

    private fun persistXrplAddress(xrplAddress: XRPLAddress): Flow<Unit> {
        return secureKeyStore.put(
            SecureItem(xrplAddress.address, xrplAddress.seed.toByteArray())
        ).flatMapMerge {
            flowOf(
                accountDao.insert(
                    AccountEntity(xrplAddress.address, "0", true)
                )
            )
        }
    }
}
