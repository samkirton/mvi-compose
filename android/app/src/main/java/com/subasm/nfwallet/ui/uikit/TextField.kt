package com.subasm.nfwallet.ui.uikit

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SingeLineTextField(
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        label = { Text(label) }
    )
}
