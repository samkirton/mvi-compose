package com.subasm.nfwallet.uikit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.subasm.nfwallet.R

@Composable
fun Toolbar(title: String, modifier: Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.h4, modifier = modifier)
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = dimensionResource(R.dimen.padding_medium))
        ) {
            Icon(
                Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.entry_settings_icon)
            )
        }
    }
}
