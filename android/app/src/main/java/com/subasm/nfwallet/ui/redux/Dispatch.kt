package com.subasm.nfwallet.ui.redux

typealias Dispatch<A> = (action: A) -> Unit

typealias Responder<VS> = (viewState: VS) -> Unit

fun <A> click(action: A, dispatch: Dispatch<A>): () -> Unit {
    return {
        dispatch(action)
    }
}

fun <A, V> clickWith(dispatch: Dispatch<A>, valueChanged: (value: V) -> A): (value: V) -> Unit {
    return { value ->
        dispatch(valueChanged(value))
    }
}
