package com.subasm.nfwallet.mxandroid.controller

import com.subasm.nfwallet.mxandroid.MxIntent
import com.subasm.nfwallet.mxandroid.MxResult
import com.subasm.nfwallet.mxandroid.MxViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.scan

interface MxReducerController<I : MxIntent, R : MxResult, VS : MxViewState> : MxBaseController<I, VS> {

    fun dispatcher(intent: I): Flow<R>

    fun reducer(previousState: VS, result: R): VS

    override fun states(initialState: VS): Flow<VS> {
        return intentSubject.flatMapMerge {
            dispatcher(it).flowOn(Dispatchers.Default)
        }.scan(initialState) { previousState, result ->
            reducer(previousState, result)
        }.distinctUntilChanged()
    }

    suspend fun viewStateProxy(pokeIntent: I, nextState: VS): VS {
        intentSubject.emit(pokeIntent)
        return nextState
    }
}
