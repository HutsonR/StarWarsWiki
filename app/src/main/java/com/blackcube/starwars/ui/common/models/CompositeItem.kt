package com.blackcube.starwars.ui.common.models

import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.models.StarshipModel

sealed class CompositeItem {
    abstract val name: String
    abstract val url: String

    data class PeopleItem(
        override val name: String,
        override val url: String,
        val gender: String,
        val starshipsCount: String,
        val date: String,
        val isFavourite: Boolean,
    ) : CompositeItem()

    data class StarshipItem(
        override val name: String,
        override val url: String,
        val model: String,
        val passengers: String,
        val pilots: String,
        val isFavourite: Boolean,
    ) : CompositeItem()
}

fun PeopleModel.toCompositeItem() = CompositeItem.PeopleItem(
    name = name,
    url = url,
    gender = gender,
    starshipsCount = starships.size.toString(),
    date = birthYear,
    isFavourite = isFavourite
)

fun StarshipModel.toCompositeItem() = CompositeItem.StarshipItem(
    name = name,
    url = url,
    model = model,
    pilots = pilots.size.toString(),
    passengers = passengers,
    isFavourite = isFavourite
)