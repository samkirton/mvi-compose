package com.subasm.xrpl.app

import android.util.Log
import com.subasm.xrpl.mxandroid.MxIntent
import com.subasm.xrpl.mxandroid.MxInterceptor
import com.subasm.xrpl.mxandroid.MxResult
import com.subasm.xrpl.mxandroid.MxViewState
import javax.inject.Inject

class LogInterceptor @Inject constructor() : MxInterceptor {

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
