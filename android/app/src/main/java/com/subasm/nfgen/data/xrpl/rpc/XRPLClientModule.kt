package com.subasm.nfgen.data.xrpl.rpc

import android.app.Application
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.subasm.nfgen.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.xrpl.xrpl4j.model.jackson.ObjectMapperFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class XRPLClientModule {

    @Provides
    @Singleton
    fun jsonRpcClientUrl(application: Application): JsonRpcClientUrl {
        return JsonRpcClientUrl(application.getString(R.string.app_xrpl_rpc_url))
    }

    @Provides
    @Singleton
    fun faucetClientUrl(application: Application): FaucetClientUrl {
        return FaucetClientUrl(application.getString(R.string.app_xrpl_faucet_url))
    }

    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun objectMapper(): ObjectMapper {
        return ObjectMapperFactory.create().registerModule(KotlinModule.Builder().build())
    }
}
