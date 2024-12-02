package com.blackcube.starwars.domain.usecases.impl

import com.blackcube.starwars.domain.models.StarshipModel
import com.blackcube.starwars.domain.repositories.StarshipRepository
import com.blackcube.starwars.domain.usecases.api.StarshipsUseCase
import javax.inject.Inject

class StarshipsUseCaseImpl @Inject constructor(
    private val repository: StarshipRepository
): StarshipsUseCase {
    override suspend fun getStarships(): List<StarshipModel> =
        repository.getStarships().results

    override suspend fun insert(url: String) =
        repository.insert(url)

    override suspend fun deleteByUrl(url: String) =
        repository.deleteByUrl(url)

    override suspend fun update(item: StarshipModel) =
        repository.update(item)
}