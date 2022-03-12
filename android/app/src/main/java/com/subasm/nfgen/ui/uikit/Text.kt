package com.subasm.nfgen.ui.uikit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.subasm.nfgen.R

@Composable
fun CopyText(
    text: String,
    copyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .padding(vertical = dimensionResource(R.dimen.padding_small))
            .fillMaxWidth()
    ) {
        SelectionContainer(Modifier.weight(1f)) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1
            )
        }
        IconButton(copyClick) {
            Icon(
                imageVector = Icons.Outlined.ContentCopy,
                contentDescription = stringResource(R.string.app_content_copy)
            )
        }
    }
}
