package com.subasm.xrpl.app.entry

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.subasm.xrpl.app.AppActivity
import com.subasm.xrpl.app.LogInterceptor
import com.subasm.xrpl.databinding.EntryActivityBinding
import com.subasm.xrpl.flowbinding.click
import com.subasm.xrpl.mxandroid.MxDelegate
import com.subasm.xrpl.mxandroid.MxInterceptor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

@AndroidEntryPoint
class EntryActivity : AppActivity<EntryActivityBinding>(), MxDelegate<EntryViewModel, EntryIntent, EntryViewState> {

    override val coroutineContext = lifecycleScope.coroutineContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listenToState(
            EntryActivity::class.simpleName,
            EntryViewState.Idle
        )
        processIntents()
    }

    override fun bindView(): EntryActivityBinding = EntryActivityBinding.inflate(layoutInflater)

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        processTickle()
    }

    override val interceptors: List<MxInterceptor> = listOf(LogInterceptor())

    override val controller: EntryViewModel by viewModels()

    override fun intents(): Flow<EntryIntent> {
        return merge(
            viewBinding.entryTrackAddressButton.click(EntryIntent.Navigate)
        )
    }

    override fun tickle(): EntryIntent = EntryIntent.Start

    override fun render(state: EntryViewState) {
        when (state) {
            EntryViewState.Idle -> Unit
            EntryViewState.Start -> Unit
            EntryViewState.Navigate -> dispatch(EntryIntent.Idle) {
                println("::navigate")
            }
        }
    }
}
