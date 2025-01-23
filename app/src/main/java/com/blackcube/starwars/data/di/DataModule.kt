package com.blackcube.starwars.data.di

import com.blackcube.starwars.data.repositories.PeopleRepositoryImpl
import com.blackcube.starwars.data.repositories.StarshipRepositoryImpl
import com.blackcube.starwars.data.repositories.UserRepositoryImpl
import com.blackcube.starwars.domain.repositories.PeopleRepository
import com.blackcube.starwars.domain.repositories.StarshipRepository
import com.blackcube.starwars.domain.repositories.UserRepository
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
    fun bindPeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    @Singleton
    fun bindStarshipRepository(starshipRepositoryImpl: StarshipRepositoryImpl): StarshipRepository

    @Binds
    @Singleton
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}
