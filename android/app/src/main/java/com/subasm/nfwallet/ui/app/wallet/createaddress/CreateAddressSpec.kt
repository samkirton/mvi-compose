package com.subasm.nfwallet.ui.app.wallet.createaddress

import com.subasm.nfwallet.ui.redux.Action
import com.subasm.nfwallet.ui.redux.Result
import com.subasm.nfwallet.ui.redux.ViewState

sealed class CreateAddressAction : Action {
    object Idle : CreateAddressAction()
    object Start : CreateAddressAction()
    object Back : CreateAddressAction()
    object GoToWallet : CreateAddressAction()
}

sealed class CreateAddressResult : Result {
    object Idle : CreateAddressResult()
    data class AddressCreated(
        val address: String,
        val seed: String
    ) : CreateAddressResult()
    object DeviceNotSupported : CreateAddressResult()
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
        object DeviceNotSupported : View()
        data class AddressCreated(
            val address: String,
            val seed: String
        ) : View()
    }
}
