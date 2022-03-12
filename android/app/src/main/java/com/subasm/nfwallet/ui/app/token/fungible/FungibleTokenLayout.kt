package com.subasm.nfwallet.ui.app.token.fungible

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.subasm.nfwallet.ui.redux.composable.ReduxComponent

@Composable
fun FungibleTokenLayout(
    viewModel: FungibleTokenViewModel,
    navController: NavController
) {
    ReduxComponent(
        initialState = FungibleTokenViewState(),
        initialAction = FungibleTokenAction.Start,
        reducer = viewModel
    ) { viewState, _ ->
        when (viewState.view) {
            FungibleTokenViewState.View.OnProgress -> Text("progress")
            is FungibleTokenViewState.View.TokenBalance -> Text("token balance")
        }
    }
}
