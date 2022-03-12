package com.subasm.nfwallet.ui.redux.controller

import com.subasm.nfwallet.ui.redux.Action
import com.subasm.nfwallet.ui.redux.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface BaseReducer<A : Action, VS : ViewState> {

    val actionSharedFlow: MutableSharedFlow<A>

    fun states(initialState: VS): Flow<VS>

    suspend fun dispatch(action: A) {
        actionSharedFlow.emit(action)
    }

    fun createMutableFlow(): MutableSharedFlow<A> {
        return MutableSharedFlow()
    }
}
