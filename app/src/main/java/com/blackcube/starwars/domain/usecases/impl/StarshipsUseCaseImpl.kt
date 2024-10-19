package com.blackcube.starwars.domain.usecases.impl

import com.blackcube.starwars.domain.models.StarShipModel
import com.blackcube.starwars.domain.repositories.StarWarsRepository
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import javax.inject.Inject

class StarshipsUseCaseImpl @Inject constructor(
    private val repository: StarWarsRepository
): StarshipsUseCase {
    override suspend operator fun invoke(): List<StarShipModel> = repository.getStarships().results
}