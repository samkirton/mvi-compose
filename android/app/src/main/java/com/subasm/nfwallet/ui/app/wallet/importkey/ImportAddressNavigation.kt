package com.subasm.nfwallet.ui.app.wallet.importkey

import androidx.navigation.NavController
import com.subasm.nfwallet.ui.app.AddressNavigationItem
import com.subasm.nfwallet.ui.redux.Dispatch

fun importAddressNavigation(
    navigate: ImportAddressViewState.Navigate,
    dispatch: Dispatch<ImportAddressAction>,
    navController: NavController
): Unit = when (navigate) {
    ImportAddressViewState.Navigate.Idle -> Unit
    ImportAddressViewState.Navigate.Back -> {
        dispatch(ImportAddressAction.Idle).apply {
            navController.popBackStack(AddressNavigationItem.Entry.route, false)
        }
    }
    ImportAddressViewState.Navigate.GoToWallet -> {
        dispatch(ImportAddressAction.Idle).apply {
            navController.navigate(AddressNavigationItem.Fungible.route)
        }
    }
}
