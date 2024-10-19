package com.blackcube.starwars.domain.usecases.api

import com.blackcube.starwars.domain.models.StarShipModel

interface StarshipsUseCase {
    suspend operator fun invoke(): List<StarShipModel>
}