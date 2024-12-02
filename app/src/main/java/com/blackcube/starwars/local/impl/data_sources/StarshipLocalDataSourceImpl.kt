package com.blackcube.starwars.local.impl.data_sources

import com.blackcube.starwars.local.api.data_sources.StarshipLocalDataSource
import com.blackcube.starwars.local.impl.database.dao.StarshipItemDao
import com.blackcube.starwars.local.impl.database.models.StarshipItemDto
import javax.inject.Inject

class StarshipLocalDataSourceImpl @Inject constructor(
    private val starshipItemDao: StarshipItemDao
): StarshipLocalDataSource {
    override suspend fun insert(item: StarshipItemDto) =
        starshipItemDao.insert(item)

    override suspend fun getAll(): List<StarshipItemDto> =
        starshipItemDao.getAll()

    override suspend fun deleteByUrl(url: String) =
        starshipItemDao.deleteByUrl(url)

    override suspend fun update(item: StarshipItemDto) =
        starshipItemDao.update(item)
}