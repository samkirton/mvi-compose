package com.subasm.nfwallet.ui.app.wallet.importkey

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
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
import com.subasm.nfwallet.R
import com.subasm.nfwallet.ui.uikit.GoBack
import com.subasm.nfwallet.ui.uikit.PrimaryButton
import com.subasm.nfwallet.ui.uikit.SingeLineTextField
import com.subasm.nfwallet.ui.uikit.Toolbar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ImportAddressLayout(
    importAddress: (seed: String) -> Unit,
    goBack: () -> Unit,
    viewStateFlow: StateFlow<ImportAddressViewState>,
) {
    var seedInput by rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Toolbar(
                title = stringResource(R.string.import_address_title),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large)),
                goBack = GoBack { goBack() }
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
                    importAddress(seedInput)
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
