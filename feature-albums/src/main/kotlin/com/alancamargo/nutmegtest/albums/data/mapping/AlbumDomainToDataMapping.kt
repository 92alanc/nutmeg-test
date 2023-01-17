package com.alancamargo.nutmegtest.albums.data.mapping

import com.alancamargo.nutmegtest.albums.data.model.database.DbAlbum
import com.alancamargo.nutmegtest.albums.domain.model.Album

internal fun Album.toDb() = DbAlbum(
    id = id,
    title = title,
    userName = userName,
    thumbnailUrl = thumbnailUrl
)
