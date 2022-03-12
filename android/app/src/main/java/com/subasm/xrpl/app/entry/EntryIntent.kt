package com.subasm.xrpl.app.entry

import com.subasm.xrpl.mxandroid.MxIntent

sealed class EntryIntent : MxIntent {
    object Idle : EntryIntent()
    object Start : EntryIntent()
    object Navigate : EntryIntent()
}