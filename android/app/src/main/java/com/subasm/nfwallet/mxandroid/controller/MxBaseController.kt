package com.subasm.nfwallet.mxandroid.controller

import com.subasm.nfwallet.mxandroid.MxIntent
import com.subasm.nfwallet.mxandroid.MxViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

interface MxBaseController<I : MxIntent, VS : MxViewState> {

    var intentSubject: MutableSharedFlow<I>

    fun states(initialState: VS): Flow<VS>

    suspend fun dispatch(intent: I) {
        intentSubject.emit(intent)
    }

    suspend fun processIntents(intents: Flow<I>) {
        intents.flowOn(Dispatchers.Default).collect {
            intentSubject.emit(it)
        }
    }

    fun createMutableFlow(): MutableSharedFlow<I> {
        return MutableSharedFlow()
    }
}
