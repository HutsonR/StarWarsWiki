package com.blackcube.starwars.remote.impl.providers

import com.blackcube.starwars.remote.impl.api.StarWarsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkProvider @Inject constructor() {
    private val provider: Retrofit

    init {
        provider = createProvider()
    }

    private fun createProvider(): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
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