package com.alancamargo.nutmegtest.albums.testtools

import com.alancamargo.nutmegtest.albums.data.model.database.DbAlbum
import com.alancamargo.nutmegtest.albums.data.model.response.AlbumResponse
import com.alancamargo.nutmegtest.albums.data.model.response.PhotoResponse
import com.alancamargo.nutmegtest.albums.data.model.response.UserResponse
import com.alancamargo.nutmegtest.albums.domain.model.Album

private const val THUMBNAIL_URL = "https://piratebay.test/photo.jpg"

internal fun stubAlbum() = Album(
    id = 1,
    title = "Album 1",
    userName = "user1",
    thumbnailUrl = THUMBNAIL_URL
)

internal fun stubDbAlbum() = DbAlbum(
    id = 1,
    title = "Album 1",
    userName = "user1",
    thumbnailUrl = THUMBNAIL_URL
)

internal fun stubAlbumResponseList() = listOf(
    AlbumResponse(
        id = 1,
        userId = 1,
        title = "Album 1"
    ),
    AlbumResponse(
        id = 2,
        userId = 1,
        title = "Album 2"
    )
)

internal fun stubDbAlbumList() = listOf(
    DbAlbum(
        id = 1,
        title = "Album 1",
        userName = "user1",
        thumbnailUrl = THUMBNAIL_URL
    ),
    DbAlbum(
        id = 2,
        title = "Album 2",
        userName = "user1",
        thumbnailUrl = THUMBNAIL_URL
    )
)

internal fun stubAlbumList() = listOf(
    Album(
        id = 1,
        title = "Album 1",
        userName = "user1",
        thumbnailUrl = THUMBNAIL_URL
    ),
    Album(
        id = 2,
        title = "Album 2",
        userName = "user1",
        thumbnailUrl = THUMBNAIL_URL
    )
)

internal fun stubUserResponse() = UserResponse(id = 1, userName = "user1")

internal fun stubPhotoResponseList() = listOf(
    PhotoResponse(
        id = 1,
        albumId = 1,
        thumbnailUrl = THUMBNAIL_URL
    ),
    PhotoResponse(
        id = 2,
        albumId = 1,
        thumbnailUrl = "https://virus-download.test/photo.exe"
    )
)
