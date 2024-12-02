package com.blackcube.starwars.local.impl.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peoples")
data class PeopleItemDto(
    @PrimaryKey val url: String
)