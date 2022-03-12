package com.subasm.nfgen.ui.app.token.fungible

import com.subasm.nfgen.domain.DomainError
import com.subasm.nfgen.domain.account.AccountBalance
import com.subasm.nfgen.ui.redux.Action
import com.subasm.nfgen.ui.redux.Result
import com.subasm.nfgen.ui.redux.ViewState

sealed class FungibleTokenAction : Action {
    object Idle : FungibleTokenAction()
    object Start : FungibleTokenAction()
}

sealed class FungibleTokenResult : Result {
    object Idle : FungibleTokenResult()
    object OnProgress : FungibleTokenResult()
    data class OnError(val error: DomainError) : FungibleTokenResult()
    data class Account(
        val accountBalance: AccountBalance
    ) : FungibleTokenResult()
}

data class FungibleTokenViewState(
    val navigate: Navigate = Navigate.Idle,
    val view: View = View.OnProgress
) : ViewState {

    sealed class Navigate {
        object Idle : Navigate()
        object Back : Navigate()
    }

    sealed class View {
        object OnProgress : View()
        data class OnError(val error: DomainError) : View()
        data class TokenBalance(
            val balance: String
        ) : View()
    }
}
