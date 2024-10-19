package com.blackcube.starwars.domain.usecases.impl

import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.repositories.StarWarsRepository
import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import javax.inject.Inject

class PeopleUseCaseImpl @Inject constructor(
    private val repository: StarWarsRepository
): PeopleUseCase {
    override suspend operator fun invoke(): List<PeopleModel> = repository.getPeoples().results
}