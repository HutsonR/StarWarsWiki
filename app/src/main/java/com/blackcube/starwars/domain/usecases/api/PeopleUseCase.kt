package com.blackcube.starwars.domain.usecases.api

import com.blackcube.starwars.domain.models.PeopleModel

interface PeopleUseCase {
    suspend fun getPeoples(): List<PeopleModel>

    suspend fun insert(url: String)

    suspend fun deleteByUrl(url: String)

    suspend fun update(item: PeopleModel)
}