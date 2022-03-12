package com.subasm.nfgen.data.xrpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.xrpl.xrpl4j.model.transactions.Address
import org.xrpl.xrpl4j.wallet.DefaultWalletFactory
import javax.inject.Inject

class GenerateWallet @Inject constructor() {

    fun generate(): Flow<Pair<Address, String>> = flow {
        val result = DefaultWalletFactory.getInstance().randomWallet(true)
        emit(result.wallet().classicAddress() to result.seed())
    }

    fun fromSeed(value: String): Flow<Pair<Address, String>> = flow {
        val result = DefaultWalletFactory.getInstance().fromSeed(value, true)
        emit(result.classicAddress() to value)
    }
}
