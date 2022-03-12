package com.subasm.nfgen.ui.uikit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.subasm.nfgen.R

data class ToolbarIconButton(
    val icon: ImageVector,
    val contentDescription: String?,
    val click: () -> Unit
)

@JvmInline
value class GoBack(
    val click: () -> Unit
)

@Composable
fun Toolbar(
    title: String,
    modifier: Modifier = Modifier,
    iconButton: ToolbarIconButton? = null,
    goBack: GoBack? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            if (goBack != null) IconButton(
                onClick = goBack.click,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back_36),
                    contentDescription = stringResource(id = R.string.app_back)
                )
            }
            Text(text = title, style = MaterialTheme.typography.h4, modifier = modifier)
        }

        if (iconButton != null) IconButton(
            onClick = iconButton.click,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = dimensionResource(R.dimen.padding_medium))
        ) {
            Icon(
                iconButton.icon,
                contentDescription = iconButton.contentDescription
            )
        }
    }
}
