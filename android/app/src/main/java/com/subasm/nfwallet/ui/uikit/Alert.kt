package com.subasm.nfwallet.ui.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.subasm.nfwallet.R

@Composable
fun SuccessAlert(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        Icon(
            imageVector = Icons.Outlined.CheckCircle,
            tint = MaterialTheme.colors.primary,
            contentDescription = stringResource(R.string.app_success),
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = dimensionResource(R.dimen.padding_large))
        )
    }
}

@Composable
fun InfoAlert(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFe5f6fd)
) {
    Row(
        modifier
            .background(backgroundColor)
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            tint = Color(0xFF111111),
            contentDescription = stringResource(R.string.app_info_content_description),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = dimensionResource(R.dimen.padding_medium))
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = dimensionResource(R.dimen.padding_large))
        )
    }
}
