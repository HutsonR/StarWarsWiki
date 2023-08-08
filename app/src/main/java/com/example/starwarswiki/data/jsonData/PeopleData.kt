package com.example.starwarswiki.data.jsonData

data class PeopleData(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<Any>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>,
    var isFavourite: Boolean
)