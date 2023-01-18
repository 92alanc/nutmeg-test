package com.alancamargo.nutmegtest.albums.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.nutmegtest.albums.data.model.database.DbAlbum

@Database(
    entities = [DbAlbum::class],
    version = 1,
    exportSchema = false
)
internal abstract class AlbumDatabase : RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao
}
