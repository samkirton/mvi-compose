package com.subasm.nfgen.ui.app.token.nft

import com.subasm.nfgen.domain.DomainError
import com.subasm.nfgen.ui.redux.Action
import com.subasm.nfgen.ui.redux.Result
import com.subasm.nfgen.ui.redux.ViewState

sealed class NonFungibleTokenAction : Action {
    object Idle : NonFungibleTokenAction()
    object Start : NonFungibleTokenAction()
    object MintNft : NonFungibleTokenAction()
}

sealed class NonFungibleTokenResult : Result {
    object Idle : NonFungibleTokenResult()
    object OnProgress : NonFungibleTokenResult()
    data class OnError(val error: DomainError) : NonFungibleTokenResult()
    object EmptyNFTCollection : NonFungibleTokenResult()
    object MintNft : NonFungibleTokenResult()
}

data class NonFungibleTokenViewState(
    val navigate: Navigate = Navigate.Idle,
    val view: View = View.OnProgress
) : ViewState {

    sealed class Navigate {
        object Idle : Navigate()
        object MintNft : Navigate()
    }

    sealed class View {
        object OnProgress : View()
        data class OnError(val error: DomainError) : View()
        object EmptyNFTCollection : View()
    }
}
