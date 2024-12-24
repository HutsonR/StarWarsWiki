package com.blackcube.starwars.local.impl.data_sources

import com.blackcube.starwars.local.api.data_sources.UserLocalDataSource
import com.blackcube.starwars.local.impl.database.dao.UserDao
import com.blackcube.starwars.local.impl.database.models.UserItemDto
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
): UserLocalDataSource {

    override suspend fun insert(user: UserItemDto) {
        userDao.insert(user)
    }

    override suspend fun getUserByUsername(username: String): UserItemDto? {
        return userDao.getUserByUsername(username)
    }
}