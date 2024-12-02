package com.blackcube.starwars.remote.impl.models

import com.blackcube.starwars.domain.models.StarshipModel
import com.google.gson.annotations.SerializedName

data class StarshipApiModel(
    @SerializedName("MGLT")
    val MGLT: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    @SerializedName("consumables")
    val consumables: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("crew")
    val crew: String,
    @SerializedName("edited")
    val edited: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("hyperdrive_rating")
    val hyperDriveRating: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("passengers")
    val passengers: String,
    @SerializedName("pilots")
    val pilots: List<Any>,
    @SerializedName("starship_class")
    val starshipClass: String,
    @SerializedName("url")
    val url: String,
)

fun StarshipApiModel.mapToStarShipModel(): StarshipModel =
    StarshipModel(
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