package com.subasm.nfwallet.ui.app.wallet.importkey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.subasm.nfwallet.ui.redux.Redux
import com.subasm.nfwallet.ui.redux.click
import com.subasm.nfwallet.ui.redux.clickWith
import com.subasm.nfwallet.ui.redux.interceptors.LoggingInterceptor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@AndroidEntryPoint
class ImportAddressActivity : ComponentActivity(), Redux<ImportAddressViewModel, ImportAddressAction, ImportAddressViewState> {

    override val coroutineContext = lifecycleScope.coroutineContext
    override val controller: ImportAddressViewModel by viewModels()
    override val stateFlow: MutableStateFlow<ImportAddressViewState> = MutableStateFlow(ImportAddressViewState())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImportAddressLayout(
                clickWith(::dispatch) { value ->
                    ImportAddressAction.ImportSeed(value)
                },
                click(ImportAddressAction.Back, ::dispatch),
                stateFlow.asStateFlow()
            )
        }
        listenToStates(
            prefix = ImportAddressActivity::class.simpleName,
            initialState = ImportAddressViewState(),
            interceptors = listOf(LoggingInterceptor())
        )
    }

    companion object {
        fun importAddressIntent(context: Context): Intent = Intent(context, ImportAddressActivity::class.java)
    }
}
