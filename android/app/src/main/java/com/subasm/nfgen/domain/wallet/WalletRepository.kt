package com.subasm.nfgen.domain.wallet

import com.subasm.nfgen.data.keystore.SecureItem
import com.subasm.nfgen.data.keystore.SecureKeyStore
import com.subasm.nfgen.data.room.dao.AccountDao
import com.subasm.nfgen.data.room.model.AccountEntity
import com.subasm.nfgen.data.xrpl.GenerateWallet
import com.subasm.nfgen.data.xrpl.rpc.account.AccountClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WalletRepository @Inject constructor(
    private val accountClient: AccountClient,
    private val generateWallet: GenerateWallet,
    private val secureKeyStore: SecureKeyStore,
    private val accountDao: AccountDao
) {

    fun createAccount(): Flow<AddressSeed> {
        return accountClient.createAccount().map {
            AddressSeed(it.account.address, it.account.secret)
        }.flatMapMerge { xrplAddress ->
            persistAddress(xrplAddress).map {
                xrplAddress
            }
        }
    }

    fun createAddress(): Flow<AddressSeed> {
        return generateWallet.generate().map {
            val (address, seed) = it
            AddressSeed(address.value(), seed)
        }.flatMapMerge { xrplAddress ->
            persistAddress(xrplAddress).map {
                xrplAddress
            }
        }
    }

    fun importSeed(seedValue: String): Flow<Unit> {
        return generateWallet.fromSeed(seedValue).map {
            val (address, seed) = it
            AddressSeed(address.value(), seed)
        }.flatMapMerge { xrplAddress ->
            persistAddress(xrplAddress)
        }
    }

    private fun persistAddress(addressSeed: AddressSeed): Flow<Unit> {
        return secureKeyStore.put(
            SecureItem(addressSeed.address, addressSeed.seed.toByteArray())
        ).flatMapMerge {
            flow {
                emit(accountDao.deselectAccounts())
            }.flatMapMerge {
                flow {
                    emit(
                        accountDao.insert(
                            AccountEntity(addressSeed.address, "0", true)
                        )
                    )
                }
            }
        }
    }
}
