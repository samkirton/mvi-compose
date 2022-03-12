package com.subasm.xrpl.app.entry

import com.subasm.xrpl.mxandroid.controller.MxController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class EntryController @Inject constructor() : MxController<EntryIntent, EntryViewState> {

    override var intentSubject: MutableSharedFlow<EntryIntent> = createMutableFlow()

    override fun dispatcher(intent: EntryIntent): Flow<EntryViewState> = when (intent) {
        EntryIntent.Idle -> flowOf(EntryViewState.Idle)
        EntryIntent.Start -> flowOf(EntryViewState.Start)
        EntryIntent.Navigate -> flowOf(EntryViewState.Navigate)
    }
}
