package com.subasm.nfwallet.ui.redux

import com.subasm.nfwallet.ui.redux.controller.BaseReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

interface Redux<C : BaseReducer<A, VS>, A : Action, VS : ViewState> : CoroutineScope {

    val controller: C

    val stateFlow: MutableStateFlow<VS>

    fun listenToStates(
        prefix: String?,
        initialState: VS,
        interceptors: List<Interceptor> = listOf()
    ) {
        launch {
            controller.states(initialState).flowOn(Dispatchers.Default).collect { state ->
                stateFlow.emit(state)
                interceptors.forEach {
                    it.intercept("mxandroid:$prefix", state)
                }
            }
        }
    }

    fun dispatch(action: A) {
        launch {
            controller.dispatch(action)
        }
    }

    fun dispatch(action: A, next: () -> Unit) {
        launch {
            controller.dispatch(action)
            next()
        }
    }
}
