package com.subasm.nfwallet.ui.app.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.subasm.nfwallet.ui.app.wallet.createaddress.CreateAddressActivity.Companion.createAddressIntent
import com.subasm.nfwallet.ui.app.wallet.importkey.ImportAddressActivity.Companion.importAddressIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntryLayout(
                goToCreateAddress = {
                    startActivity(createAddressIntent(this))
                },
                goToImportAddress = {
                    startActivity(importAddressIntent(this))
                },
                goToSettings = {
                }
            )
        }
    }
}
