package com.subasm.nfgen.data.xrpl.rpc

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.EMPTY_REQUEST
import okio.IOException
import javax.inject.Inject

data class FaucetClientUrl(
    val value: String
)

data class FaucetAccount(
    val xAddress: String,
    val secret: String,
    val classicAddress: String,
    val address: String
)

data class FaucetResult(
    val account: FaucetAccount,
    val amount: Int,
    val balance: Int
)

class FaucetClient @Inject constructor(
    private val client: OkHttpClient,
    private val url: FaucetClientUrl,
    private val objectMapper: ObjectMapper
) {

    @Throws(IOException::class)
    fun post(): Flow<FaucetResult> {
        return call(
            Request.Builder()
                .url(url.value)
                .post(EMPTY_REQUEST)
                .build()
        )
    }

    private fun call(request: Request): Flow<FaucetResult> = flow {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            emit(objectMapper.readValue(response.body?.string(), FaucetResult::class.java))
        } else {
            throw JsonRpcException("fatal_error")
        }
    }
}
