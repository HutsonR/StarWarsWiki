package com.blackcube.starwars.domain.models

import com.blackcube.starwars.local.impl.database.models.StarshipItemDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class StarshipModel(
    val MGLT: String,
    val cargoCapacity: String,
    val consumables: String,
    val costInCredits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperDriveRating: String,
    val length: String,
    val manufacturer: String,
    val maxAtmospheringSpeed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starshipClass: String,
    val url: String,
    val isFavourite: Boolean = false
): DomainModel()

fun StarshipModel.mapToDto() = StarshipItemDto(
    url = url
)