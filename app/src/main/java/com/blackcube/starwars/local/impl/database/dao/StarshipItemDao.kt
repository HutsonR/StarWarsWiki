package com.blackcube.starwars.local.impl.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.blackcube.starwars.local.impl.database.models.StarshipItemDto

@Dao
interface StarshipItemDao {
    @Insert
    suspend fun insert(item: StarshipItemDto)

    @Query("SELECT * FROM starships WHERE url = :url")
    fun getData(url: String): StarshipItemDto

    @Query("SELECT * FROM starships ORDER BY url DESC")
    fun getAll(): List<StarshipItemDto>

    @Query("DELETE FROM starships WHERE url = :url")
    suspend fun deleteByUrl(url: String)

    @Update
    fun update(item: StarshipItemDto)
}
