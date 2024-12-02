package com.blackcube.starwars.data.repositories

import com.blackcube.starwars.domain.models.StarShipsListModel
import com.blackcube.starwars.domain.models.StarshipModel
import com.blackcube.starwars.domain.models.mapToDto
import com.blackcube.starwars.domain.repositories.StarshipRepository
import com.blackcube.starwars.local.api.data_sources.StarshipLocalDataSource
import com.blackcube.starwars.local.impl.database.models.StarshipItemDto
import com.blackcube.starwars.remote.api.data_sources.StarWarsRemoteDataSource
import javax.inject.Inject

class StarshipRepositoryImpl @Inject constructor(
    private val remoteDataSource: StarWarsRemoteDataSource,
    private val starshipLocalDataSource: StarshipLocalDataSource
): StarshipRepository {

    override suspend fun getStarships(): StarShipsListModel {
        val savedData = starshipLocalDataSource.getAll().map { it.url }
        val updatedStarships = remoteDataSource.getStarships().results.map { remoteData ->
            remoteData.copy(
                isFavourite = savedData.contains(remoteData.url)
            )
        }

        return StarShipsListModel(results = updatedStarships)
    }

    override suspend fun insert(url: String) =
        starshipLocalDataSource.insert(StarshipItemDto(url))

    override suspend fun deleteByUrl(url: String) =
        starshipLocalDataSource.deleteByUrl(url)

    override suspend fun update(item: StarshipModel) =
        starshipLocalDataSource.update(item.mapToDto())

}