package com.subasm.nfwallet.ui.app.entry

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.subasm.nfwallet.R
import com.subasm.nfwallet.ui.uikit.PrimaryButton
import com.subasm.nfwallet.ui.uikit.SecondaryButton
import com.subasm.nfwallet.ui.uikit.Toolbar
import com.subasm.nfwallet.ui.uikit.ToolbarIconButton

@Composable
fun EntryLayout(
    goToCreateAddress: () -> Unit,
    goToImportAddress: () -> Unit,
    goToSettings: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Toolbar(
                title = stringResource(R.string.entry_title),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large)),
                iconButton = ToolbarIconButton(
                    icon = Icons.Outlined.Settings,
                    contentDescription = stringResource(R.string.entry_settings_icon),
                    click = goToSettings
                )
            )
            Text(
                text = stringResource(R.string.entry_description),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_large),
                    vertical = 0.dp
                )
            )
            PrimaryButton(
                label = stringResource(R.string.entry_create_address),
                onClick = goToCreateAddress,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_mega),
                    start = dimensionResource(R.dimen.padding_large)
                )
            )
            SecondaryButton(
                label = stringResource(R.string.entry_import_address),
                onClick = goToImportAddress,
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
