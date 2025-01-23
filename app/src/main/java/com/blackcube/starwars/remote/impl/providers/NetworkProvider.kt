package com.blackcube.starwars.remote.impl.providers

import android.content.Context
import com.blackcube.starwars.remote.impl.api.StarWarsApi
import com.blackcube.starwars.remote.impl.interceptors.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkProvider @Inject constructor(private val context: Context) {
    private val provider: Retrofit

    init {
        provider = createProvider()
    }

    private fun createProvider(): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val mockInterceptor = MockInterceptor(context)

        val client = OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createStarWarsApi(): StarWarsApi = provider.create(StarWarsApi::class.java)

    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"
    }
}