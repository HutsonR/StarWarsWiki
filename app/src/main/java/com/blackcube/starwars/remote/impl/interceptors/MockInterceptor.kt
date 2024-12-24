package com.blackcube.starwars.remote.impl.interceptors

import android.content.Context
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class MockInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()

        if (uri.contains("people")) {
            val json = loadJsonFromAssets("people.json")
            return Response.Builder()
                .code(200)
                .message("Mocked response")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create("application/json".toMediaTypeOrNull(), json))
                .build()
        }

        if (uri.contains("starships")) {
            val json = loadJsonFromAssets("starship.json")
            return Response.Builder()
                .code(200)
                .message("Mocked response")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create("application/json".toMediaTypeOrNull(), json))
                .build()
        }


        return chain.proceed(chain.request())
    }

    private fun loadJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
