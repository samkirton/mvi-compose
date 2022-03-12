package com.subasm.nfgen.ui.redux.interceptors

import android.util.Log
import com.subasm.nfgen.ui.redux.Action
import com.subasm.nfgen.ui.redux.Interceptor
import com.subasm.nfgen.ui.redux.Result
import com.subasm.nfgen.ui.redux.ViewState
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(label: String, action: Action) {
        Log.d(label, action.toString())
    }

    override fun intercept(label: String, result: Result) {
        Log.d(label, result.toString())
    }

    override fun intercept(label: String, state: ViewState) {
        Log.d(label, state.toString())
    }
}
