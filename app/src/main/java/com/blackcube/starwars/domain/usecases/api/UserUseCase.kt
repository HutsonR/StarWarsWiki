package com.blackcube.starwars.domain.usecases.api

import com.blackcube.starwars.domain.models.User

interface UserUseCase {
    suspend fun registerUser(user: User): Boolean
    suspend fun loginUser(username: String, password: String): User?
}