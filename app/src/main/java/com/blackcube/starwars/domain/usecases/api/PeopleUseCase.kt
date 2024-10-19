package com.blackcube.starwars.domain.usecases.api

import com.blackcube.starwars.domain.models.PeopleModel

interface PeopleUseCase {
    suspend operator fun invoke(): List<PeopleModel>
}