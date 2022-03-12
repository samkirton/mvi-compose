package com.subasm.nfgen.ui.app.wallet.createaddress

import androidx.navigation.NavController
import com.subasm.nfgen.ui.app.NavigationItem
import com.subasm.nfgen.ui.redux.Dispatch

fun createAddressNavigation(
    navigate: CreateAddressViewState.Navigate,
    dispatch: Dispatch<CreateAddressAction>,
    navController: NavController
): Unit = when (navigate) {
    CreateAddressViewState.Navigate.Idle -> Unit
    CreateAddressViewState.Navigate.Back -> dispatch(CreateAddressAction.Idle).apply {
        navController.popBackStack(NavigationItem.Entry.route, false)
    }
    CreateAddressViewState.Navigate.GoToWallet -> dispatch(CreateAddressAction.Idle).apply {
        navController.navigate(NavigationItem.Fungible.route)
    }
}
