package com.subasm.nfgen.ui.redux.controller

import com.subasm.nfgen.ui.redux.Action
import com.subasm.nfgen.ui.redux.Result
import com.subasm.nfgen.ui.redux.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.scan

interface Reducer<A : Action, R : Result, VS : ViewState> {

    val actionSharedFlow: MutableSharedFlow<A>

    fun dispatcher(action: A): Flow<R>

    fun reducer(previousState: VS, result: R): VS

    suspend fun dispatch(action: A) {
        actionSharedFlow.emit(action)
    }

    fun states(initialState: VS, initialAction: A): Flow<VS> {
        return actionSharedFlow.onSubscription {
            emit(initialAction)
        }.flatMapMerge {
            dispatcher(it).flowOn(Dispatchers.IO)
        }.scan(initialState, ::reducer).distinctUntilChanged().flowOn(Dispatchers.Default)
    }

    fun createMutableFlow(): MutableSharedFlow<A> {
        return MutableSharedFlow()
    }
}
