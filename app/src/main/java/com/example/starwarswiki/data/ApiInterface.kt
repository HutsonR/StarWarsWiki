package com.example.starwarswiki.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("people/")
    fun getPeople(@Query("search") name: CharSequence?): Call<PeoplesResponse>

    @GET("starships/")
    fun getStarships(@Query("search") name: CharSequence?): Call<StarshipsResponse>

    companion object {

        var BASE_URL = "https://swapi.dev/api/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}
