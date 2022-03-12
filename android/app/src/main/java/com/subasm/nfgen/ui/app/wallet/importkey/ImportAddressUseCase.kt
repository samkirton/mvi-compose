package com.subasm.nfgen.ui.app.wallet.importkey

import com.subasm.nfgen.domain.wallet.WalletRepository
import com.subasm.nfgen.ui.domainError
import com.subasm.nfgen.ui.start
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImportAddressUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {

    fun importSeed(seed: String): Flow<ImportAddressResult> {
        return walletRepository.importSeed(seed).map {
            ImportAddressResult.SeedImported
        }.domainError {
            ImportAddressResult.OnError(it)
        }.start(ImportAddressResult.OnProgress)
    }
}
