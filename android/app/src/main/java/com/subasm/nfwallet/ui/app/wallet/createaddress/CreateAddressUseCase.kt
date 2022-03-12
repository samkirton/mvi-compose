package com.subasm.nfwallet.ui.app.wallet.createaddress

import android.util.Log
import com.subasm.nfwallet.domain.wallet.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CreateAddressUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {

    fun generateAddress(): Flow<CreateAddressResult> {
        return walletRepository.createAddress().map {
            CreateAddressResult.AddressCreated(
                it.address,
                it.seed
            )
        }.catch { e ->
            Log.e(CreateAddressUseCase::class.simpleName, e.toString())
            CreateAddressResult.DeviceNotSupported
        }
    }
}
