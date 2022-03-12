package com.subasm.nfgen.ui.app.wallet.createaddress

import com.subasm.nfgen.domain.wallet.WalletRepository
import com.subasm.nfgen.ui.domainError
import com.subasm.nfgen.ui.start
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CreateAddressUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {

    fun generateAddress(): Flow<CreateAddressResult> {
        return walletRepository.createAccount().map {
            CreateAddressResult.AddressCreated(
                it.address,
                it.seed
            )
        }.domainError {
            CreateAddressResult.OnError(it)
        }.start(CreateAddressResult.OnProgress)
    }
}
