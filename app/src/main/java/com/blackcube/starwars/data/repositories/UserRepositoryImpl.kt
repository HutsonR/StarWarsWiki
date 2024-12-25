package com.blackcube.starwars.data.repositories

import com.blackcube.starwars.domain.models.User
import com.blackcube.starwars.domain.repositories.UserRepository
import com.blackcube.starwars.local.api.data_sources.UserLocalDataSource
import com.blackcube.starwars.local.impl.database.models.asEntity
import com.blackcube.starwars.local.impl.database.models.asExternalModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {

    override suspend fun registerUser(user: User) {
        userLocalDataSource.insert(user.asEntity())
    }

    override suspend fun loginUser(username: String, password: String): User? {
        val userDto = userLocalDataSource.getUserByUsername(username)
        return if (userDto != null && userDto.password == password) {
            userDto.asExternalModel()
        } else {
            null
        }
    }
}
