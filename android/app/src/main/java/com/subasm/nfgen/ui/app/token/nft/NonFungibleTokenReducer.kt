package com.subasm.nfgen.ui.app.token.nft

import com.subasm.nfgen.ui.redux.controller.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NonFungibleTokenReducer @Inject constructor(
    private val fungibleTokenUseCase: NonFungibleTokenUseCase
) : Reducer<NonFungibleTokenAction, NonFungibleTokenResult, NonFungibleTokenViewState> {

    override val actionSharedFlow: MutableSharedFlow<NonFungibleTokenAction> = createMutableFlow()

    override fun dispatcher(action: NonFungibleTokenAction): Flow<NonFungibleTokenResult> = when (action) {
        NonFungibleTokenAction.Idle -> flowOf(NonFungibleTokenResult.Idle)
        NonFungibleTokenAction.Start -> fungibleTokenUseCase.getAccount()
        NonFungibleTokenAction.MintNft -> flowOf(NonFungibleTokenResult.MintNft)
    }

    override fun reducer(
        previousState: NonFungibleTokenViewState,
        result: NonFungibleTokenResult
    ): NonFungibleTokenViewState = when (result) {
        NonFungibleTokenResult.Idle -> previousState.copy(
            navigate = NonFungibleTokenViewState.Navigate.Idle
        )
        NonFungibleTokenResult.MintNft -> previousState.copy(
            navigate = NonFungibleTokenViewState.Navigate.MintNft
        )
        NonFungibleTokenResult.OnProgress -> previousState.copy(
            view = NonFungibleTokenViewState.View.OnProgress
        )
        is NonFungibleTokenResult.EmptyNFTCollection -> previousState.copy(
            view = NonFungibleTokenViewState.View.EmptyNFTCollection
        )
        is NonFungibleTokenResult.OnError -> previousState.copy(
            view = NonFungibleTokenViewState.View.OnError(result.error)
        )
    }
}
