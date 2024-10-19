package com.blackcube.starwars.data.di

import com.blackcube.starwars.data.repositories.StarWarsRepositoryImpl
import com.blackcube.starwars.domain.repositories.StarWarsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindStarWarsRepository(starWarsRepositoryImpl: StarWarsRepositoryImpl): StarWarsRepository

}
