package com.blackcube.starwars.local.di

import com.blackcube.starwars.local.api.data_sources.StarWarsLocalDataSource
import com.blackcube.starwars.local.impl.data_sources.StarWarsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideStarWarsRemoteDataSource(): StarWarsLocalDataSource {
        return StarWarsLocalDataSourceImpl()
    }

}