package com.alancamargo.nutmegtest.albums.domain.usecase

import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.alancamargo.nutmegtest.albums.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetAlbumsUseCaseImpl @Inject constructor(
    private val repository: AlbumRepository
) : GetAlbumsUseCase {

    override fun invoke(): Flow<AlbumListResult> = flow {
        val albums = repository.getAlbums()
        emit(albums)
    }
}
