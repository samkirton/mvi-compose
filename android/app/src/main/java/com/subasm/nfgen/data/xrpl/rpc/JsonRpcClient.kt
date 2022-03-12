package com.subasm.nfgen.data.xrpl.rpc

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.xrpl.xrpl4j.model.client.XrplRequestParams
import org.xrpl.xrpl4j.model.client.XrplResult
import javax.inject.Inject

data class JsonRpcClientUrl(
    val value: String
)

data class JsonRpc(
    val method: String,
    val params: List<XrplRequestParams>
)

class JsonRpcException(message: String) : Exception(message)

class JsonRpcClient @Inject constructor(
    private val client: OkHttpClient,
    private val url: JsonRpcClientUrl,
    private val objectMapper: ObjectMapper
) {

    private val jsonMediaType: MediaType = "application/json; charset=utf-8".toMediaType()

    @Throws(IOException::class)
    fun <T : XrplResult> post(json: JsonRpc, resultType: Class<T>): Flow<T> {
        return call(
            Request.Builder()
                .url(url.value)
                .post(objectMapper.writeValueAsString(json).toRequestBody(jsonMediaType))
                .build(),
            resultType
        )
    }

    private fun <T : XrplResult> call(request: Request, resultType: Class<T>): Flow<T> = flow {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val responseNode = objectMapper.readTree(response.body?.string() ?: "")
            val resultNode = responseNode.get("result")
            if (resultNode.has("error")) {
                val errorMessage = resultNode.get("error_exception") ?: resultNode.get("error_message")
                throw JsonRpcException(errorMessage.textValue() ?: "")
            } else {
                emit(objectMapper.readValue(resultNode.toString(), resultType))
            }
        } else {
            throw JsonRpcException("fatal_error")
        }
    }
}
