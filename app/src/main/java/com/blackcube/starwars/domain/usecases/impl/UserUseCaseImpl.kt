package com.blackcube.starwars.domain.usecases.impl

import com.blackcube.starwars.domain.models.User
import com.blackcube.starwars.domain.repositories.UserRepository
import com.blackcube.starwars.domain.usecases.api.UserUseCase
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
): UserUseCase {

    override suspend fun registerUser(user: User): Boolean {
        val userInfo = userRepository.loginUser(user.username, user.password)
        return if (userInfo == null) {
            userRepository.registerUser(user)
            true
        } else false
    }

    override suspend fun loginUser(username: String, password: String): User? {
        return userRepository.loginUser(username, password)
    }
}
