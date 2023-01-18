package com.alancamargo.nutmegtest.albums.di

import com.alancamargo.nutmegtest.albums.data.database.AlbumDao
import com.alancamargo.nutmegtest.albums.data.database.AlbumDatabase
import com.alancamargo.nutmegtest.albums.data.service.AlbumService
import com.alancamargo.nutmegtest.core.database.LocalDatabaseProvider
import com.alancamargo.nutmegtest.core.network.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AlbumsDataModule {

    @Provides
    @Singleton
    fun provideAlbumService(apiProvider: ApiProvider): AlbumService {
        return apiProvider.provideService(AlbumService::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumDao(localDatabaseProvider: LocalDatabaseProvider): AlbumDao {
        val database = localDatabaseProvider.provideDatabase(
            AlbumDatabase::class,
            databaseName = "albums"
        )

        return database.getAlbumDao()
    }
}
