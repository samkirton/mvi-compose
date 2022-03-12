package com.subasm.nfgen.ui.app.token.fungible

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.subasm.nfgen.R
import com.subasm.nfgen.ui.redux.composable.ReduxComponent
import com.subasm.nfgen.ui.uikit.ErrorAlertFullScreen
import com.subasm.nfgen.ui.uikit.FullScreenProgressIndicator

@Composable
fun FungibleTokenLayout(
    viewModel: FungibleTokenViewModel
) {
    ReduxComponent(
        initialState = FungibleTokenViewState(),
        initialAction = FungibleTokenAction.Start,
        reducer = viewModel
    ) { viewState, dispatch ->
        when (viewState.view) {
            FungibleTokenViewState.View.OnProgress -> FullScreenProgressIndicator()
            is FungibleTokenViewState.View.TokenBalance -> FungibleTokenBalance(viewState.view.balance)
            is FungibleTokenViewState.View.OnError -> ErrorAlertFullScreen(
                viewState.view.error,
                onClick = {
                    dispatch(FungibleTokenAction.Start)
                }
            )
        }
    }
}

@Composable
fun FungibleTokenBalance(
    balance: String
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.padding_mega))
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_xrp_coin),
            contentDescription = stringResource(R.string.fungible_token_balance),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.fungible_token_balance),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = dimensionResource(R.dimen.padding_large))
        )
        Text(
            balance,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}
