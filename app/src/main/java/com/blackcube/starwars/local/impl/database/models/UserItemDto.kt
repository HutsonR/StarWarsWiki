package com.blackcube.starwars.local.impl.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blackcube.starwars.domain.models.User
import java.util.UUID

@Entity(tableName = "users",)
data class UserItemDto(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val username: String,
    val password: String,
)

fun UserItemDto.asExternalModel() = User(
    id = id,
    username = username,
    password = password
)
fun User.asEntity() = UserItemDto(
    id = id.ifBlank { UUID.randomUUID().toString() },
    username = username,
    password = password
)