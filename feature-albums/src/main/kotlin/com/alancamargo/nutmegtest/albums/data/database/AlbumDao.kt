package com.alancamargo.nutmegtest.albums.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alancamargo.nutmegtest.albums.data.model.database.DbAlbum

@Dao
internal interface AlbumDao {

    @Query("SELECT * FROM ALBUMS")
    suspend fun getAlbums(): List<DbAlbum>?

    @Insert(entity = DbAlbum::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlbum(album: DbAlbum)
}
