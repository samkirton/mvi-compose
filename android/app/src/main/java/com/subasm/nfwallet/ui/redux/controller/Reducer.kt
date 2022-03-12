package com.subasm.nfwallet.ui.redux.controller

import com.subasm.nfwallet.ui.redux.Action
import com.subasm.nfwallet.ui.redux.Result
import com.subasm.nfwallet.ui.redux.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan

interface Reducer<A : Action, R : Result, VS : ViewState> : BaseReducer<A, VS> {

    fun dispatcher(action: A): Flow<R>

    fun reducer(previousState: VS, result: R): VS

    override fun states(initialState: VS): Flow<VS> {
        return actionSharedFlow.flatMapMerge {
            dispatcher(it).flowOn(Dispatchers.Default)
        }.scan(initialState) { previousState, result ->
            reducer(previousState, result)
        }.onStart {
            emit(initialState)
        }
    }
}
