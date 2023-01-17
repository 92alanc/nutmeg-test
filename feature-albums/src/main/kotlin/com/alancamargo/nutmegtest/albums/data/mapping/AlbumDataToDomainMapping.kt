package com.alancamargo.nutmegtest.albums.data.mapping

import com.alancamargo.nutmegtest.albums.data.model.response.AlbumResponse
import com.alancamargo.nutmegtest.albums.domain.model.Album

internal fun AlbumResponse.toDomain(
    userName: String,
    thumbnailUrl: String
) = Album(
    id = id,
    title = title,
    userName = userName,
    thumbnailUrl = thumbnailUrl
)
