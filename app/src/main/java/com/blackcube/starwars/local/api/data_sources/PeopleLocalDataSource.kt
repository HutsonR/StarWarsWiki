package com.blackcube.starwars.local.api.data_sources

import com.blackcube.starwars.local.impl.database.models.PeopleItemDto

interface PeopleLocalDataSource {
    suspend fun insert(item: PeopleItemDto)

    suspend fun getAll(): List<PeopleItemDto>

    suspend fun deleteByUrl(url: String)

    suspend fun update(item: PeopleItemDto)
}