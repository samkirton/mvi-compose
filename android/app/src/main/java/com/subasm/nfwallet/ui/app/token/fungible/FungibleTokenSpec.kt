package com.subasm.nfwallet.ui.app.token.fungible

import com.subasm.nfwallet.ui.redux.Action
import com.subasm.nfwallet.ui.redux.Result
import com.subasm.nfwallet.ui.redux.ViewState

sealed class FungibleTokenAction : Action {
    object Idle : FungibleTokenAction()
    object Start : FungibleTokenAction()
}

sealed class FungibleTokenResult : Result {
    object Idle : FungibleTokenResult()
}

data class FungibleTokenViewState(
    val navigate: Navigate = Navigate.Idle,
    val view: View = View.OnProgress
) : ViewState {

    sealed class Navigate {
        object Idle : Navigate()
        object Start : Navigate()
        object Back : Navigate()
    }

    sealed class View {
        object OnProgress : View()
        data class TokenBalance(
            val balance: String
        ) : View()
    }
}
