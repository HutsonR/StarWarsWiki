package com.blackcube.starwars.domain.models

import com.blackcube.starwars.local.impl.database.models.PeopleItemDto

data class PeopleModel(
    val birthYear: String,
    val created: String,
    val edited: String,
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    val hairColor: String,
    val height: String,
    val homeWorld: String,
    val mass: String,
    val name: String,
    val skinColor: String,
    val species: List<Any>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>,
    val isFavourite: Boolean = false
)

fun PeopleModel.mapToDto() = PeopleItemDto(
    url = url
)