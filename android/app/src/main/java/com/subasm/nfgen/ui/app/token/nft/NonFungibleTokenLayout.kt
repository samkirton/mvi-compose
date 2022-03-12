package com.subasm.nfgen.ui.app.token.nft

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.subasm.nfgen.R
import com.subasm.nfgen.ui.redux.Dispatch
import com.subasm.nfgen.ui.redux.composable.ReduxComponent
import com.subasm.nfgen.ui.uikit.ErrorAlertFullScreen
import com.subasm.nfgen.ui.uikit.FullScreenProgressIndicator
import com.subasm.nfgen.ui.uikit.SecondaryButton

@Composable
fun NonFungibleTokenLayout(
    viewModel: NonFungibleTokenViewModel,
    navController: NavController
) {
    ReduxComponent(
        initialState = NonFungibleTokenViewState(),
        initialAction = NonFungibleTokenAction.Start,
        reducer = viewModel
    ) { viewState, dispatch ->
        nonFungibleTokenNavigation(viewState.navigate, dispatch, navController)
        when (viewState.view) {
            NonFungibleTokenViewState.View.OnProgress -> FullScreenProgressIndicator()
            is NonFungibleTokenViewState.View.EmptyNFTCollection -> EmptyNFTCollectionView(dispatch)
            is NonFungibleTokenViewState.View.OnError -> ErrorAlertFullScreen(
                viewState.view.error,
                onClick = {
                    dispatch(NonFungibleTokenAction.Start)
                }
            )
        }
    }
}

@Composable
fun EmptyNFTCollectionView(
    dispatch: Dispatch<NonFungibleTokenAction>
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Icon(
                painter = painterResource(R.drawable.ic_poker_cards_24),
                contentDescription = stringResource(R.string.fungible_token_balance),
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.non_fungible_empty_collection),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(dimensionResource(R.dimen.padding_large))
            )
            SecondaryButton(
                stringResource(R.string.non_fungible_mint),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                dispatch(NonFungibleTokenAction.MintNft)
            }
        }
    }
}
