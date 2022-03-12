package com.subasm.nfwallet.ui.app.wallet.createaddress

import androidx.navigation.NavController
import com.subasm.nfwallet.ui.app.AddressNavigationItem
import com.subasm.nfwallet.ui.redux.Dispatch

fun createAddressNavigation(
    navigate: CreateAddressViewState.Navigate,
    dispatch: Dispatch<CreateAddressAction>,
    navController: NavController
): Unit = when (navigate) {
    CreateAddressViewState.Navigate.Idle -> Unit
    CreateAddressViewState.Navigate.Back -> dispatch(CreateAddressAction.Idle).apply {
        navController.popBackStack(AddressNavigationItem.Entry.route, false)
    }
    CreateAddressViewState.Navigate.GoToWallet -> dispatch(CreateAddressAction.Idle).apply {
        navController.navigate(AddressNavigationItem.Fungible.route)
    }
}
