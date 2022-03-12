package com.subasm.xrpl.app.entry

import androidx.lifecycle.ViewModel
import com.subasm.xrpl.mxandroid.controller.MxController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    entryController: EntryController
) : ViewModel(), MxController<EntryIntent, EntryViewState> by entryController
