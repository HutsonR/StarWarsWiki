package com.blackcube.starwars.remote.impl.mappers

import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.models.PeoplesListModel
import com.blackcube.starwars.domain.models.StarShipModel
import com.blackcube.starwars.domain.models.StarShipsListModel
import com.blackcube.starwars.remote.impl.models.PeopleApiModel
import com.blackcube.starwars.remote.impl.models.PeoplesListApiModel
import com.blackcube.starwars.remote.impl.models.StarShipApiModel
import com.blackcube.starwars.remote.impl.models.StarShipsListApiModel

fun PeoplesListApiModel.mapToPeoplesListModel(): PeoplesListModel =
    PeoplesListModel(results = results.map { it.mapToPeopleModel() })

fun PeopleApiModel.mapToPeopleModel(): PeopleModel =
    PeopleModel(
        birthYear,
        created,
        edited,
        eyeColor,
        films,
        gender,
        hairColor,
        height,
        homeWorld,
        mass,
        name,
        skinColor,
        species,
        starships,
        url,
        vehicles
    )

fun StarShipsListApiModel.mapToStarShipsListModel(): StarShipsListModel =
    StarShipsListModel(results = results.map { it.mapToStarShipModel() })

fun StarShipApiModel.mapToStarShipModel(): StarShipModel =
    StarShipModel(
        MGLT,
        cargoCapacity,
        consumables,
        costInCredits,
        created,
        crew,
        edited,
        films,
        hyperDriveRating,
        length,
        manufacturer,
        maxAtmospheringSpeed,
        model,
        name,
        passengers,
        pilots,
        starshipClass,
        url
    )