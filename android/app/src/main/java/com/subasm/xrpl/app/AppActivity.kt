package com.subasm.xrpl.app

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.subasm.xrpl.R

abstract class AppActivity<VB : ViewBinding> : FragmentActivity() {

    lateinit var viewBinding: VB

    abstract fun bindView(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = bindView()
        setContentView(viewBinding.root)
        super.onCreate(savedInstanceState)
        setupViews()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    open fun setupViews() {
    }

    internal fun showIsDirtyDialog() {
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

    internal fun showOkDialog(title: String, body: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(body)
            .setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    internal fun showOptionDialog(title: String, body: String, positive: () -> Unit) {
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

    internal fun showKeyboard(view: EditText) {
        view.run {
            requestFocus()
            postDelayed({
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .showSoftInput(this, 0)
            }, 200)
        }
    }

    companion object {
        const val PARCEL = "PARCEL"
    }
}
