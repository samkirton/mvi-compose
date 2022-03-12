package com.subasm.nfwallet.mxandroid.interceptors

import android.util.Log
import com.subasm.nfwallet.mxandroid.MxIntent
import com.subasm.nfwallet.mxandroid.MxInterceptor
import com.subasm.nfwallet.mxandroid.MxResult
import com.subasm.nfwallet.mxandroid.MxViewState
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : MxInterceptor {

    override fun intercept(label: String, intent: MxIntent) {
        Log.d(label, intent.toString())
    }

    override fun intercept(label: String, result: MxResult) {
        Log.d(label, result.toString())
    }

    override fun intercept(label: String, state: MxViewState) {
        Log.d(label, state.toString())
    }
}
