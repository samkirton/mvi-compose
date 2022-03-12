package com.subasm.nfwallet.ui.redux.interceptors

import android.util.Log
import com.subasm.nfwallet.ui.redux.Action
import com.subasm.nfwallet.ui.redux.Interceptor
import com.subasm.nfwallet.ui.redux.Result
import com.subasm.nfwallet.ui.redux.ViewState
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
