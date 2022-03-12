package com.subasm.nfgen.ui.app.wallet.createaddress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.subasm.nfgen.R
import com.subasm.nfgen.ui.redux.Dispatch
import com.subasm.nfgen.ui.redux.composable.ReduxComponent
import com.subasm.nfgen.ui.uikit.ErrorAlertFullScreen
import com.subasm.nfgen.ui.uikit.GoBack
import com.subasm.nfgen.ui.uikit.InfoAlert
import com.subasm.nfgen.ui.uikit.PrimaryButton
import com.subasm.nfgen.ui.uikit.SmallProgressIndicator
import com.subasm.nfgen.ui.uikit.SuccessAlert
import com.subasm.nfgen.ui.uikit.Toolbar

@Composable
fun CreateAddressLayout(
    viewModel: CreateAddressViewModel,
    navController: NavController
) {
    ReduxComponent(
        initialState = CreateAddressViewState(),
        initialAction = CreateAddressAction.Start,
        reducer = viewModel
    ) { viewState, dispatch ->
        LaunchedEffect(viewState.navigate) {
            createAddressNavigation(viewState.navigate, dispatch, navController)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                painter = painterResource(R.drawable.ic_cta_home_magenta),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd),
                tint = Color.Unspecified
            )
            Column {
                Toolbar(
                    title = stringResource(R.string.create_address_title),
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_large)),
                    goBack = GoBack { dispatch(CreateAddressAction.Back) }
                )
                Box(
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
                ) {
                    CreateAddressView(viewState.view, dispatch)
                }
            }
        }
    }
}

@Composable
fun CreateAddressView(
    view: CreateAddressViewState.View,
    dispatch: Dispatch<CreateAddressAction>
) {
    when (view) {
        CreateAddressViewState.View.OnProgress -> CreateAddressLoading()
        is CreateAddressViewState.View.AddressCreated -> AddressCreated(
            view.address,
            view.seed,
            dispatch
        )
        is CreateAddressViewState.View.OnError -> ErrorAlertFullScreen(
            view.error,
            onClick = {
                dispatch(CreateAddressAction.Start)
            }
        )
    }
}

@Composable
fun CreateAddressLoading() {
    Row {
        SmallProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(R.string.create_address_progress),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = dimensionResource(R.dimen.padding_large))
        )
    }
}

@Composable
fun AddressCreated(
    address: String,
    seed: String,
    dispatch: Dispatch<CreateAddressAction>
) {
    Column {
        SuccessAlert(stringResource(R.string.create_address_created))
        AddressCreatedDetails(address, seed, dispatch)
    }
}

@Composable
fun AddressCreatedDetails(
    address: String,
    seed: String,
    dispatch: Dispatch<CreateAddressAction>
) {
    Column {
        Text(
            text = stringResource(R.string.create_address_address_label),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_giant))
        )
        Text(
            text = address,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = stringResource(R.string.create_address_seed_label),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_large))
        )
        SelectionContainer {
            Text(
                text = seed,
                style = MaterialTheme.typography.body1
            )
        }
        InfoAlert(
            text = stringResource(R.string.create_address_seed_warning),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_large))
        )
        PrimaryButton(
            label = stringResource(R.string.create_address_continue_button),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_large)),
            onClick = {
                dispatch(CreateAddressAction.GoToWallet)
            }
        )
    }
}

@Composable
fun DeviceNotSupported() {
    Box {
    }
}
