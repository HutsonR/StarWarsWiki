package com.blackcube.starwars.remote.impl.data_sources

import com.blackcube.starwars.domain.models.PeoplesListModel
import com.blackcube.starwars.domain.models.StarShipsListModel
import com.blackcube.starwars.remote.api.data_sources.StarWarsRemoteDataSource
import com.blackcube.starwars.remote.impl.api.StarWarsApi
import com.blackcube.starwars.remote.impl.models.mapToPeoplesListModel
import com.blackcube.starwars.remote.impl.models.mapToStarShipsListModel
import javax.inject.Inject

class StarWarsRemoteDataSourceImpl @Inject constructor(
    private val starWarsApi: StarWarsApi
): StarWarsRemoteDataSource {

    override suspend fun getPeoples(): PeoplesListModel =
        starWarsApi.getPeople().mapToPeoplesListModel()

    override suspend fun getStarships(): StarShipsListModel =
        starWarsApi.getStarships().mapToStarShipsListModel()

}