package com.subasm.xrpl.flowbinding

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.isActive

fun <I> View.click(intent: I): Flow<I> = channelFlow {
    setOnClickListener {
        if (isActive) { trySend(intent) }
    }
    awaitClose { setOnClickListener(null) }
}
