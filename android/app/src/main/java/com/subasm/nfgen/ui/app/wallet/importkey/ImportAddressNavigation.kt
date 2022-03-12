package com.subasm.nfgen.ui.app.wallet.importkey

import androidx.navigation.NavController
import com.subasm.nfgen.ui.app.NavigationItem
import com.subasm.nfgen.ui.redux.Dispatch

fun importAddressNavigation(
    navigate: ImportAddressViewState.Navigate,
    dispatch: Dispatch<ImportAddressAction>,
    navController: NavController
): Unit = when (navigate) {
    ImportAddressViewState.Navigate.Idle -> Unit
    ImportAddressViewState.Navigate.Back -> {
        dispatch(ImportAddressAction.Idle).apply {
            navController.popBackStack(NavigationItem.Entry.route, false)
        }
    }
    ImportAddressViewState.Navigate.GoToWallet -> {
        dispatch(ImportAddressAction.Idle).apply {
            navController.navigate(NavigationItem.Fungible.route)
        }
    }
}
