package com.subasm.nfwallet.app.entry

import androidx.lifecycle.ViewModel
import com.subasm.nfwallet.mxandroid.MxIntent
import com.subasm.nfwallet.mxandroid.MxViewState
import com.subasm.nfwallet.mxandroid.controller.MxController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class EntryIntent : MxIntent {
    object Idle : EntryIntent()
    object Start : EntryIntent()
    object GoToImportAddress : EntryIntent()
    object GoToCreateNewAddress : EntryIntent()
}

sealed class EntryViewState : MxViewState {
    object Idle : EntryViewState()
    object Start : EntryViewState()
    object GoToCreateNewAddress : EntryViewState()
    object GoToImportAddress : EntryViewState()
}

@HiltViewModel
class EntryViewModel @Inject constructor(
    entryController: EntryController
) : ViewModel(), MxController<EntryIntent, EntryViewState> by entryController
