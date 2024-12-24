package com.blackcube.starwars.local.impl.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blackcube.starwars.local.impl.database.models.UserItemDto

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserItemDto)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserItemDto?
}