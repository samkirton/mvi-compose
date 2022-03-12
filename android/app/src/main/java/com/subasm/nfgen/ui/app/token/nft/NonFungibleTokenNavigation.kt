package com.subasm.nfgen.ui.app.token.nft

import androidx.navigation.NavController
import com.subasm.nfgen.ui.app.NavigationItem
import com.subasm.nfgen.ui.redux.Dispatch

fun nonFungibleTokenNavigation(
    navigate: NonFungibleTokenViewState.Navigate,
    dispatch: Dispatch<NonFungibleTokenAction>,
    navController: NavController
): Unit = when (navigate) {
    NonFungibleTokenViewState.Navigate.Idle -> Unit
    NonFungibleTokenViewState.Navigate.MintNft -> dispatch(NonFungibleTokenAction.Idle).apply {
        navController.navigate(NavigationItem.MintNonFungible.route)
    }
}
