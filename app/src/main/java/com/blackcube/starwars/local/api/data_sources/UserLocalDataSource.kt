package com.blackcube.starwars.local.api.data_sources

import com.blackcube.starwars.local.impl.database.models.UserItemDto

interface UserLocalDataSource {
    suspend fun insert(user: UserItemDto)
    suspend fun getUserByUsername(username: String): UserItemDto?
}