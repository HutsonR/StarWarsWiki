package com.blackcube.starwars.remote.impl.models

import com.blackcube.starwars.domain.models.PeopleModel
import com.google.gson.annotations.SerializedName

data class PeopleApiModel(
    @SerializedName("birth_year")
    val birthYear: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("edited")
    val edited: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("homeworld")
    val homeWorld: String,
    @SerializedName("mass")
    val mass: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("species")
    val species: List<String>,
    @SerializedName("starships")
    val starships: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("vehicles")
    val vehicles: List<String>
)

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