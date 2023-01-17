package com.alancamargo.nutmegtest.albums.di

import com.alancamargo.nutmegtest.albums.data.remote.AlbumRemoteDataSource
import com.alancamargo.nutmegtest.albums.data.remote.AlbumRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class AlbumsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAlbumRemoteDataSource(impl: AlbumRemoteDataSourceImpl): AlbumRemoteDataSource
}
