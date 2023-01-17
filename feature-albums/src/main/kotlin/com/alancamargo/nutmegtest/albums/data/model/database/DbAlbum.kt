package com.alancamargo.nutmegtest.albums.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ALBUMS")
internal data class DbAlbum(
    @PrimaryKey val id: Long,
    val title: String,
    val userName: String,
    val thumbnailUrl: String
)
