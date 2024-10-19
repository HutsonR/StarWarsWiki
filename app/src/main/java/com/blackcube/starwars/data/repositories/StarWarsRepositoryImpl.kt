package com.blackcube.starwars.data.repositories

import com.blackcube.starwars.domain.models.PeoplesListModel
import com.blackcube.starwars.domain.models.StarShipsListModel
import com.blackcube.starwars.domain.repositories.StarWarsRepository
import com.blackcube.starwars.local.api.data_sources.StarWarsLocalDataSource
import com.blackcube.starwars.remote.api.data_sources.StarWarsRemoteDataSource
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val remoteDataSource: StarWarsRemoteDataSource,
    private val localDataSource: StarWarsLocalDataSource
): StarWarsRepository {

    override suspend fun getPeoples(): PeoplesListModel {
        // todo сделать сохранение и доставать из localDataSource
        return remoteDataSource.getPeoples()
    }

    override suspend fun getStarships(): StarShipsListModel {
        // todo сделать сохранение и доставать из localDataSource
        return remoteDataSource.getStarships()
    }

}