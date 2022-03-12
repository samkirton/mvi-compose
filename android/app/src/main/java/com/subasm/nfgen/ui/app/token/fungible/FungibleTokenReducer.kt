package com.subasm.nfgen.ui.app.token.fungible

import com.subasm.nfgen.ui.redux.controller.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FungibleTokenReducer @Inject constructor(
    private val fungibleTokenUseCase: FungibleTokenUseCase
) : Reducer<FungibleTokenAction, FungibleTokenResult, FungibleTokenViewState> {

    override val actionSharedFlow: MutableSharedFlow<FungibleTokenAction> = createMutableFlow()

    override fun dispatcher(action: FungibleTokenAction): Flow<FungibleTokenResult> = when (action) {
        FungibleTokenAction.Idle -> flowOf(FungibleTokenResult.Idle)
        FungibleTokenAction.Start -> fungibleTokenUseCase.getAccount()
    }

    override fun reducer(
        previousState: FungibleTokenViewState,
        result: FungibleTokenResult
    ): FungibleTokenViewState = when (result) {
        FungibleTokenResult.Idle -> previousState.copy(
            navigate = FungibleTokenViewState.Navigate.Idle
        )
        FungibleTokenResult.OnProgress -> previousState.copy(
            view = FungibleTokenViewState.View.OnProgress
        )
        is FungibleTokenResult.Account -> previousState.copy(
            view = FungibleTokenViewState.View.TokenBalance(result.accountBalance.balance)
        )
        is FungibleTokenResult.OnError -> previousState.copy(
            view = FungibleTokenViewState.View.OnError(result.error)
        )
    }
}
