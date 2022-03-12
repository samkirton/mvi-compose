package com.subasm.nfgen.ui.app.wallet.importkey

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.subasm.nfgen.R
import com.subasm.nfgen.ui.redux.composable.ReduxComponent
import com.subasm.nfgen.ui.uikit.GoBack
import com.subasm.nfgen.ui.uikit.PrimaryButton
import com.subasm.nfgen.ui.uikit.SingeLineTextField
import com.subasm.nfgen.ui.uikit.Toolbar

@Composable
fun ImportAddressLayout(
    viewModel: ImportAddressViewModel,
    navController: NavController
) {
    var seedInput by rememberSaveable { mutableStateOf("") }
    ReduxComponent(
        initialState = ImportAddressViewState(),
        initialAction = ImportAddressAction.Idle,
        reducer = viewModel
    ) { viewState, dispatch ->
        LaunchedEffect(viewState.navigate) {
            importAddressNavigation(viewState.navigate, dispatch, navController)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Toolbar(
                    title = stringResource(R.string.import_address_title),
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_large)),
                    goBack = GoBack { dispatch(ImportAddressAction.Back) }
                )
                SingeLineTextField(
                    stringResource(R.string.import_address_input_seed_label),
                    onValueChange = { value ->
                        seedInput = value
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_large))
                        .fillMaxWidth()
                )
                PrimaryButton(
                    label = stringResource(R.string.import_address_button),
                    onClick = {
                        dispatch(ImportAddressAction.ImportSeed(seedInput))
                    },
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_large)
                    )
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_home_green),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd),
                tint = Color.Unspecified
            )
        }
    }
}
