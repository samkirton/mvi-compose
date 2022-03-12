package com.subasm.nfwallet.ui.app.wallet.importkey

import com.subasm.nfwallet.ui.redux.Action
import com.subasm.nfwallet.ui.redux.Result
import com.subasm.nfwallet.ui.redux.ViewState

sealed class ImportAddressAction : Action {
    object Idle : ImportAddressAction()
    object Back : ImportAddressAction()
    data class ImportSeed(
        val seed: String
    ) : ImportAddressAction()
}

sealed class ImportAddressResult : Result {
    object Idle : ImportAddressResult()
    object DeviceNotSupported : ImportAddressResult()
    object SeedImported : ImportAddressResult()
    object SeedExists : ImportAddressResult()
    object InvalidSeed : ImportAddressResult()
    object Back : ImportAddressResult()
}

data class ImportAddressViewState(
    val navigate: Navigate = Navigate.Idle,
    val view: View = View.OnProgress,
    val seedInput: String = ""
) : ViewState {

    sealed class Navigate {
        object Idle : Navigate()
        object GoToWallet : Navigate()
        object Back : Navigate()
    }

    sealed class View {
        object OnProgress : View()
        object DeviceNotSupported : View()
        object SeedExists : View()
        object InvalidSeed : View()
    }
}
