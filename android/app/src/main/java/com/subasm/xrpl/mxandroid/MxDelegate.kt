package com.subasm.xrpl.mxandroid

import com.subasm.xrpl.mxandroid.controller.MxBaseController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

interface MxDelegate<C : MxBaseController<I, VS>, I : MxIntent, VS : MxViewState> : CoroutineScope {

    val interceptors: List<MxInterceptor>
    val controller: C

    fun intents(): Flow<I>

    fun tickle(): I?

    fun render(state: VS)

    fun listenToState(label: String?, initialState: VS) {
        launch {
            controller.states(initialState).flowOn(Dispatchers.Default).collect { state ->
                render(state)
                interceptors.forEach {
                    it.intercept("mxandroid:$label", state)
                }
            }
        }
    }

    fun processIntents() {
        launch {
            controller.processIntents(intents())
        }
    }

    fun processTickle() {
        launch {
            tickle()?.let {
                controller.dispatch(it)
            }
        }
    }

    fun dispatch(pokeIntent: I, next: () -> Unit) {
        launch {
            controller.dispatch(pokeIntent)
            next()
        }
    }
}
