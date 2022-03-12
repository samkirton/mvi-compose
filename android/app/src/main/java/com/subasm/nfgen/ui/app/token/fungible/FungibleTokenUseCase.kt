package com.subasm.nfgen.ui.app.token.fungible

import com.subasm.nfgen.domain.account.AccountRepository
import com.subasm.nfgen.ui.domainError
import com.subasm.nfgen.ui.start
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FungibleTokenUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccount(): Flow<FungibleTokenResult> {
        return accountRepository.getAccountBalance().map {
            FungibleTokenResult.Account(it)
        }.domainError {
            FungibleTokenResult.OnError(it)
        }.start(FungibleTokenResult.OnProgress)
    }
}
