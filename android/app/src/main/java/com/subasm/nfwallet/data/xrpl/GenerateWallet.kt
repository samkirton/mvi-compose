package com.subasm.nfwallet.data.xrpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.xrpl.xrpl4j.model.transactions.XAddress
import org.xrpl.xrpl4j.wallet.DefaultWalletFactory
import javax.inject.Inject

class GenerateWallet @Inject constructor() {

    fun generate(): Flow<Pair<XAddress, String>> = flow {
        val result = DefaultWalletFactory.getInstance().randomWallet(true)
        emit(result.wallet().xAddress() to result.seed())
    }

    fun fromSeed(value: String): Flow<Pair<XAddress, String>> = flow {
        val result = DefaultWalletFactory.getInstance().fromSeed(value, true)
        emit(result.xAddress() to value)
    }
}
