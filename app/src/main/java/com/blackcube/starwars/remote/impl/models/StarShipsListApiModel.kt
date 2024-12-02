package com.blackcube.starwars.remote.impl.models

import com.blackcube.starwars.domain.models.StarShipsListModel

data class StarShipsListApiModel (
    val results: List<StarshipApiModel>
)

fun StarShipsListApiModel.mapToStarShipsListModel(): StarShipsListModel =
    StarShipsListModel(results = results.map { it.mapToStarShipModel() })