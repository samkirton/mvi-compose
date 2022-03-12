package com.subasm.xrpl.app.entry

import com.subasm.xrpl.mxandroid.MxViewState

sealed class EntryViewState : MxViewState {
    object Idle : EntryViewState()
    object Start : EntryViewState()
    object Navigate : EntryViewState()
}
