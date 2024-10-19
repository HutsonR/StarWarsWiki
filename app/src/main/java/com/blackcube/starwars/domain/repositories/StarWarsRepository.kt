package com.blackcube.starwars.domain.repositories

import com.blackcube.starwars.domain.models.PeoplesListModel
import com.blackcube.starwars.domain.models.StarShipsListModel

interface StarWarsRepository {
    suspend fun getPeoples(): PeoplesListModel
    suspend fun getStarships(): StarShipsListModel
}