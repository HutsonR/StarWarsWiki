package com.blackcube.starwars.local.impl.data_sources

import com.blackcube.starwars.local.api.data_sources.PeopleLocalDataSource
import com.blackcube.starwars.local.impl.database.dao.PeopleItemDao
import com.blackcube.starwars.local.impl.database.models.PeopleItemDto
import javax.inject.Inject

class PeopleLocalDataSourceImpl @Inject constructor(
    private val peopleItemDao: PeopleItemDao
): PeopleLocalDataSource {
    override suspend fun insert(item: PeopleItemDto) =
        peopleItemDao.insert(item)

    override suspend fun getAll(): List<PeopleItemDto> =
        peopleItemDao.getAll()

    override suspend fun deleteByUrl(url: String) =
        peopleItemDao.deleteByUrl(url)


    override suspend fun update(item: PeopleItemDto) =
        peopleItemDao.update(item)
}