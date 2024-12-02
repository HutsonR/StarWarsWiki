package com.blackcube.starwars.domain.usecases.api

import com.blackcube.starwars.domain.models.StarshipModel

interface StarshipsUseCase {
    suspend fun getStarships(): List<StarshipModel>

    suspend fun insert(url: String)

    suspend fun deleteByUrl(url: String)

    suspend fun update(item: StarshipModel)
}