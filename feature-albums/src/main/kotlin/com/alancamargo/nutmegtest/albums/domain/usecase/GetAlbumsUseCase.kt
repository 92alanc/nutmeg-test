package com.alancamargo.nutmegtest.albums.domain.usecase

import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import kotlinx.coroutines.flow.Flow

internal interface GetAlbumsUseCase {

    operator fun invoke(): Flow<AlbumListResult>
}
