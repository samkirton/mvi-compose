package com.subasm.nfgen.ui.app.wallet.createaddress

import com.subasm.nfgen.domain.DomainError
import com.subasm.nfgen.ui.redux.Action
import com.subasm.nfgen.ui.redux.Result
import com.subasm.nfgen.ui.redux.ViewState

sealed class CreateAddressAction : Action {
    object Idle : CreateAddressAction()
    object Start : CreateAddressAction()
    object Back : CreateAddressAction()
    object GoToWallet : CreateAddressAction()
}

sealed class CreateAddressResult : Result {
    object Idle : CreateAddressResult()
    object OnProgress : CreateAddressResult()
    data class AddressCreated(
        val address: String,
        val seed: String
    ) : CreateAddressResult()
    data class OnError(
        val error: DomainError
    ) : CreateAddressResult()
    object Back : CreateAddressResult()
    object GoToWallet : CreateAddressResult()
}

data class CreateAddressViewState(
    val navigate: Navigate = Navigate.Idle,
    val view: View = View.OnProgress
) : ViewState {

    sealed class Navigate {
        object Idle : Navigate()
        object GoToWallet : Navigate()
        object Back : Navigate()
    }

    sealed class View {
        object OnProgress : View()
        data class OnError(
            val error: DomainError
        ) : View()
        data class AddressCreated(
            val address: String,
            val seed: String
        ) : View()
    }
}
