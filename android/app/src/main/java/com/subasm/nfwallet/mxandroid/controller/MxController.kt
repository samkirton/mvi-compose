package com.subasm.nfwallet.mxandroid.controller

import com.subasm.nfwallet.mxandroid.MxIntent
import com.subasm.nfwallet.mxandroid.MxViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart

interface MxController<I : MxIntent, VS : MxViewState> : MxBaseController<I, VS> {

    fun dispatcher(intent: I): Flow<VS>

    override fun states(initialState: VS): Flow<VS> {
        return intentSubject.flatMapMerge {
            merge(dispatcher(it)).flowOn(Dispatchers.IO)
        }.onStart {
            emit(initialState)
        }
    }
}
