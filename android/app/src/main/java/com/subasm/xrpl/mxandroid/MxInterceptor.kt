package com.subasm.xrpl.mxandroid

interface MxInterceptor {
    fun intercept(label: String, intent: MxIntent)
    fun intercept(label: String, result: MxResult)
    fun intercept(label: String, state: MxViewState)
}
