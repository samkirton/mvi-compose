package com.subasm.nfgen.ui

import android.util.Log
import com.subasm.nfgen.domain.DomainError
import com.subasm.nfgen.domain.domainErrorHandler
import com.subasm.nfgen.ui.redux.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

fun <T : Result> Flow<T>.domainError(result: (domainError: DomainError) -> T): Flow<T> {
    return catch { e ->
        Log.e(Flow::class.simpleName, e.toString())
        emit(result(domainErrorHandler(e)))
    }
}

fun <T : Result> Flow<T>.start(result: T): Flow<T> {
    return onStart {
        emit(result)
    }
}
