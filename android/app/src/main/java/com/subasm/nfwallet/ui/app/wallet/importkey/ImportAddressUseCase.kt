package com.subasm.nfwallet.ui.app.wallet.importkey

import android.util.Log
import com.subasm.nfwallet.domain.wallet.WalletRepository
import com.subasm.nfwallet.ui.app.wallet.createaddress.CreateAddressResult
import com.subasm.nfwallet.ui.app.wallet.createaddress.CreateAddressUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImportAddressUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {

    fun importSeed(seed: String): Flow<ImportAddressResult> {
        return walletRepository.importSeed(seed).map {
            ImportAddressResult.SeedImported
        }.catch { e ->
            Log.e(CreateAddressUseCase::class.simpleName, e.toString())
            CreateAddressResult.DeviceNotSupported
        }
    }
}
