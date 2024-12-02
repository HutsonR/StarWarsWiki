package com.blackcube.starwars.data.repositories

import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.models.PeoplesListModel
import com.blackcube.starwars.domain.models.mapToDto
import com.blackcube.starwars.domain.repositories.PeopleRepository
import com.blackcube.starwars.local.api.data_sources.PeopleLocalDataSource
import com.blackcube.starwars.local.impl.database.models.PeopleItemDto
import com.blackcube.starwars.remote.api.data_sources.StarWarsRemoteDataSource
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val remoteDataSource: StarWarsRemoteDataSource,
    private val peopleLocalDataSource: PeopleLocalDataSource
): PeopleRepository {

    override suspend fun getPeoples(): PeoplesListModel {
        val savedData = peopleLocalDataSource.getAll().map { it.url }
        val updatedPeoples = remoteDataSource.getPeoples().results.map { remoteData ->
            remoteData.copy(
                isFavourite = savedData.contains(remoteData.url)
            )
        }

        return PeoplesListModel(results = updatedPeoples)
    }

    override suspend fun insert(url: String) =
        peopleLocalDataSource.insert(PeopleItemDto(url))

    override suspend fun deleteByUrl(url: String) =
        peopleLocalDataSource.deleteByUrl(url)

    override suspend fun update(item: PeopleModel) =
        peopleLocalDataSource.update(item.mapToDto())

}