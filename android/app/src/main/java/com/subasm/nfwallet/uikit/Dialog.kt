package com.subasm.nfwallet.app

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.subasm.nfwallet.R

fun ComponentActivity.showIsDirtyDialog() {
    MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.app_dirty_changes_title))
        .setMessage(getString(R.string.app_dirty_changes_body))
        .setNegativeButton(getString(android.R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        .setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        .show()
}

fun ComponentActivity.showOkDialog(title: String, body: String) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(body)
        .setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

fun ComponentActivity.showOptionDialog(title: String, body: String, positive: () -> Unit) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(body)
        .setNegativeButton(getString(android.R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        .setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            positive()
        }
        .show()
}

internal fun ComponentActivity.showKeyboard(view: EditText) {
    view.run {
        requestFocus()
        postDelayed({
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(this, 0)
        }, 200)
    }
}
