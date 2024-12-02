package com.blackcube.starwars.remote.impl.models

import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.models.PeoplesListModel

data class PeoplesListApiModel (
    val results: List<PeopleApiModel>
)

fun PeoplesListApiModel.mapToPeoplesListModel(): PeoplesListModel =
    PeoplesListModel(results = results.map { it.mapToPeopleModel() })