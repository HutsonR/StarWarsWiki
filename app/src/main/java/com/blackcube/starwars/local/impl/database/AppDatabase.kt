package com.blackcube.starwars.local.impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blackcube.starwars.local.impl.database.dao.PeopleItemDao
import com.blackcube.starwars.local.impl.database.dao.StarshipItemDao
import com.blackcube.starwars.local.impl.database.models.PeopleItemDto
import com.blackcube.starwars.local.impl.database.models.StarshipItemDto

@Database(entities = [PeopleItemDto::class, StarshipItemDto::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract val peopleItemDao: PeopleItemDao
    abstract val starshipItemDao: StarshipItemDao

    companion object {
        const val DATABASE_NAME = "starwars_database"
    }
}