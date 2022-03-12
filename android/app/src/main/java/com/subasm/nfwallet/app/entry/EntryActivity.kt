package com.subasm.nfwallet.app.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.subasm.nfwallet.mxandroid.MxDelegate
import com.subasm.nfwallet.mxandroid.MxInterceptor
import com.subasm.nfwallet.mxandroid.interceptors.LoggingInterceptor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

@AndroidEntryPoint
class EntryActivity : ComponentActivity(), MxDelegate<EntryViewModel, EntryIntent, EntryViewState> {

    override val coroutineContext = lifecycleScope.coroutineContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntryLayout()
        }
        listenToState(
            EntryActivity::class.simpleName,
            EntryViewState.Idle
        )
        processIntents()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        processTickle()
    }

    override val interceptors: List<MxInterceptor> = listOf(LoggingInterceptor())

    override val controller: EntryViewModel by viewModels()

    override fun intents(): Flow<EntryIntent> {
        return merge()
    }

    override fun tickle(): EntryIntent = EntryIntent.Start

    override fun render(state: EntryViewState) {
        when (state) {
            EntryViewState.Idle -> Unit
            EntryViewState.Start -> Unit
            EntryViewState.GoToCreateNewAddress -> dispatch(EntryIntent.Idle) {
                println("::createNewAddress")
            }
            EntryViewState.GoToImportAddress -> dispatch(EntryIntent.Idle) {
                println("::importAddress")
            }
        }
    }
}
