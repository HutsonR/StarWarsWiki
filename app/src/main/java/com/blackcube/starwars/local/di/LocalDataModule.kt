package com.blackcube.starwars.local.di

import android.app.Application
import androidx.room.Room
import com.blackcube.starwars.local.api.data_sources.PeopleLocalDataSource
import com.blackcube.starwars.local.api.data_sources.StarshipLocalDataSource
import com.blackcube.starwars.local.impl.data_sources.PeopleLocalDataSourceImpl
import com.blackcube.starwars.local.impl.data_sources.StarshipLocalDataSourceImpl
import com.blackcube.starwars.local.impl.database.AppDatabase
import com.blackcube.starwars.local.impl.database.dao.PeopleItemDao
import com.blackcube.starwars.local.impl.database.dao.StarshipItemDao
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
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePeopleItemDao(appDatabase: AppDatabase): PeopleItemDao {
        return appDatabase.peopleItemDao
    }

    @Provides
    @Singleton
    fun provideStarshipItemDao(appDatabase: AppDatabase): StarshipItemDao {
        return appDatabase.starshipItemDao
    }

    @Provides
    @Singleton
    fun providePeopleLocalDataSource(peopleItemDao: PeopleItemDao): PeopleLocalDataSource {
        return PeopleLocalDataSourceImpl(peopleItemDao)
    }

    @Provides
    @Singleton
    fun provideStarshipLocalDataSource(starshipItemDao: StarshipItemDao): StarshipLocalDataSource {
        return StarshipLocalDataSourceImpl(starshipItemDao)
    }

}