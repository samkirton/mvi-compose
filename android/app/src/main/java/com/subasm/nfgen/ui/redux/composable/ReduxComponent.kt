package com.subasm.nfgen.ui.redux.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.subasm.nfgen.ui.redux.Action
import com.subasm.nfgen.ui.redux.Dispatch
import com.subasm.nfgen.ui.redux.Result
import com.subasm.nfgen.ui.redux.ViewState
import com.subasm.nfgen.ui.redux.controller.Reducer
import kotlinx.coroutines.launch

@Composable
fun <VS : ViewState, A : Action, R : Result> ReduxComponent(
    initialState: VS,
    initialAction: A,
    reducer: Reducer<A, R, VS>,
    content: @Composable (viewState: VS, dispatch: Dispatch<A>) -> Unit
) {
    val viewStateFlow = remember {
        reducer.states(initialState, initialAction)
    }
    val coroutineScope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewStateFlowLifecycle = remember(viewStateFlow, lifecycleOwner) {
        viewStateFlow
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val viewState by viewStateFlowLifecycle.collectAsState(initialState)

    LaunchedEffect(viewState) {
        println(viewState)
    }

    content(viewState) { action ->
        coroutineScope.launch {
            reducer.dispatch(action)
        }
    }
}
