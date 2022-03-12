package com.subasm.nfwallet.ui.redux

import com.subasm.nfwallet.ui.redux.controller.Reducer
import kotlinx.coroutines.flow.MutableStateFlow

interface Redux<A : Action, R : Result, VS : ViewState> {

    val reducer: Reducer<A, R, VS>

    val viewStateFlow: MutableStateFlow<VS>

    suspend fun listenToStates(
        prefix: String?,
        initialState: VS,
        interceptors: List<Interceptor> = listOf()
    ) {
    }

    suspend fun dispatch(action: A) {
        reducer.dispatch(action)
    }

    suspend fun dispatch(action: A, next: () -> Unit) {
        reducer.dispatch(action)
        next()
    }

    fun responder(viewState: VS)
}
