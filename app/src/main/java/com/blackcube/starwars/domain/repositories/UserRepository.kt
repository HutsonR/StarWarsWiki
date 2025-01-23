package com.blackcube.starwars.domain.repositories

import com.blackcube.starwars.domain.models.User

interface UserRepository {
    suspend fun registerUser(user: User)
    suspend fun loginUser(username: String, password: String): User?
}