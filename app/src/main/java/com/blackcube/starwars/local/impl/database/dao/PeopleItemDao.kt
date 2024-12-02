package com.blackcube.starwars.local.impl.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.blackcube.starwars.local.impl.database.models.PeopleItemDto

@Dao
interface PeopleItemDao {
    @Insert
    suspend fun insert(item: PeopleItemDto)

    @Query("SELECT * FROM peoples WHERE url = :url")
    fun getData(url: String): PeopleItemDto

    @Query("SELECT * FROM peoples ORDER BY url DESC")
    fun getAll(): List<PeopleItemDto>

    @Query("DELETE FROM peoples WHERE url = :url")
    suspend fun deleteByUrl(url: String)

    @Update
    fun update(item: PeopleItemDto)
}
