package com.subasm.nfwallet.ui.app.wallet.createaddress

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.subasm.nfwallet.ui.redux.Redux
import com.subasm.nfwallet.ui.redux.interceptors.LoggingInterceptor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@AndroidEntryPoint
class CreateAddressActivity : ComponentActivity(), Redux<CreateAddressViewModel, CreateAddressAction, CreateAddressViewState> {

    override val coroutineContext = lifecycleScope.coroutineContext
    override val controller: CreateAddressViewModel by viewModels()
    override val stateFlow: MutableStateFlow<CreateAddressViewState> = MutableStateFlow(CreateAddressViewState())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateAddressLayout(
                stateFlow.asStateFlow(),
                ::dispatch,
                ::responder
            )
        }
        listenToStates(
            prefix = CreateAddressActivity::class.simpleName,
            initialState = CreateAddressViewState(),
            interceptors = listOf(LoggingInterceptor())
        )
    }

    private fun responder(viewState: CreateAddressViewState) = when (viewState.navigate) {
        CreateAddressViewState.Navigate.Idle -> Unit
        CreateAddressViewState.Navigate.Back -> dispatch(CreateAddressAction.Idle) {
            finish()
        }
        CreateAddressViewState.Navigate.GoToWallet -> dispatch(CreateAddressAction.Idle) {
            println("::goToWallet")
        }
    }

    companion object {
        fun createAddressIntent(context: Context): Intent = Intent(context, CreateAddressActivity::class.java)
    }
}
