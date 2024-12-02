package com.blackcube.starwars.local.impl.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starships")
data class StarshipItemDto(
    @PrimaryKey val url: String
)