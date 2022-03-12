package com.subasm.nfgen.domain

import com.subasm.nfgen.data.xrpl.rpc.JsonRpcException

sealed class DomainError {
    data class Message(val message: String) : DomainError()
    object Network : DomainError()
    object Fatal : DomainError()
}

fun domainErrorHandler(e: Throwable): DomainError {
    return if (e is JsonRpcException) {
        when (val error = e.message) {
            "network_error" -> DomainError.Network
            null -> DomainError.Fatal
            else -> DomainError.Message(error)
        }
    } else {
        DomainError.Fatal
    }
}
