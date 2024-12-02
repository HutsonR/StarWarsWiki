package com.blackcube.starwars.local.api.data_sources

import com.blackcube.starwars.local.impl.database.models.StarshipItemDto

interface StarshipLocalDataSource {
    suspend fun insert(item: StarshipItemDto)

    suspend fun getAll(): List<StarshipItemDto>

    suspend fun deleteByUrl(url: String)

    suspend fun update(item: StarshipItemDto)
}