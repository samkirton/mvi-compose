package com.subasm.nfwallet.ui.app.token.fungible

import com.subasm.nfwallet.ui.redux.controller.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FungibleTokenReducer @Inject constructor() : Reducer<FungibleTokenAction, FungibleTokenResult, FungibleTokenViewState> {

    override val actionSharedFlow: MutableSharedFlow<FungibleTokenAction> = createMutableFlow()

    override fun dispatcher(action: FungibleTokenAction): Flow<FungibleTokenResult> = when (action) {
        FungibleTokenAction.Idle -> flowOf(FungibleTokenResult.Idle)
        FungibleTokenAction.Start -> flowOf(FungibleTokenResult.Idle)
    }

    override fun reducer(
        previousState: FungibleTokenViewState,
        result: FungibleTokenResult
    ): FungibleTokenViewState = when (result) {
        FungibleTokenResult.Idle -> previousState.copy(
            navigate = FungibleTokenViewState.Navigate.Idle
        )
    }
}
