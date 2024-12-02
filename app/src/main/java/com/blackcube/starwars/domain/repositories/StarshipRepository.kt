package com.blackcube.starwars.domain.repositories

import com.blackcube.starwars.domain.models.StarshipModel
import com.blackcube.starwars.domain.models.StarShipsListModel

interface StarshipRepository {
    suspend fun getStarships(): StarShipsListModel

    suspend fun insert(url: String)

    suspend fun deleteByUrl(url: String)

    suspend fun update(item: StarshipModel)
}