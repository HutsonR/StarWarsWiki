package com.blackcube.starwars.remote.impl.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKeyRequest = chain
            .request()
            .newBuilder()
            .header(X_API_KEY, apiKey)
            .build()

        return chain.proceed(apiKeyRequest)
    }

    companion object {
        private const val X_API_KEY = "X-Api-Key"
    }
}