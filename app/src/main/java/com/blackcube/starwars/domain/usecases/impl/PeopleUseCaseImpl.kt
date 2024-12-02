package com.blackcube.starwars.domain.usecases.impl

import com.blackcube.starwars.domain.models.PeopleModel
import com.blackcube.starwars.domain.repositories.PeopleRepository
import com.blackcube.starwars.domain.usecases.api.PeopleUseCase
import javax.inject.Inject

class PeopleUseCaseImpl @Inject constructor(
    private val repository: PeopleRepository
): PeopleUseCase {
    override suspend fun getPeoples(): List<PeopleModel> =
        repository.getPeoples().results

    override suspend fun insert(url: String) =
        repository.insert(url)

    override suspend fun deleteByUrl(url: String) =
        repository.deleteByUrl(url)

    override suspend fun update(item: PeopleModel) =
        repository.update(item)
}