package com.blackcube.starwars.remote.api.data_sources

import com.blackcube.starwars.domain.models.PeoplesListModel
import com.blackcube.starwars.domain.models.StarShipsListModel

interface StarWarsRemoteDataSource {

    suspend fun getPeoples(): PeoplesListModel

    suspend fun getStarships(): StarShipsListModel

}