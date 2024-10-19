package com.blackcube.starwars.remote.impl.api

import com.blackcube.starwars.remote.impl.models.PeoplesListApiModel
import com.blackcube.starwars.remote.impl.models.StarShipsListApiModel
import retrofit2.http.GET

interface StarWarsApi {

    @GET("people")
    suspend fun getPeople(): PeoplesListApiModel

    @GET("starships")
    suspend fun getStarships(): StarShipsListApiModel

}