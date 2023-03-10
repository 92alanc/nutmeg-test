package com.alancamargo.nutmegtest.albums.data.local

import com.alancamargo.nutmegtest.albums.data.database.AlbumDao
import com.alancamargo.nutmegtest.albums.testtools.stubAlbum
import com.alancamargo.nutmegtest.albums.testtools.stubAlbumList
import com.alancamargo.nutmegtest.albums.testtools.stubDbAlbum
import com.alancamargo.nutmegtest.albums.testtools.stubDbAlbumList
import com.alancamargo.nutmegtest.core.log.Logger
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AlbumLocalDataSourceImplTest {

    private val mockDao = mockk<AlbumDao>(relaxed = true)
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val localDataSource = AlbumLocalDataSourceImpl(mockDao, mockLogger)

    @Test
    fun `getAlbums should return albums from dao as domain`() {
        // GIVEN
        coEvery { mockDao.getAlbums() } returns stubDbAlbumList()

        // WHEN
        val actual = runBlocking { localDataSource.getAlbums() }

        // THEN
        val expected = stubAlbumList()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `saveAlbum should insert album in dao`() {
        // WHEN
        val album = stubAlbum()
        runBlocking { localDataSource.saveAlbum(album) }

        // THEN
        val expected = stubDbAlbum()
        coVerify { mockDao.insertAlbum(expected) }
    }

    @Test
    fun `when dao throws exception saveAlbum should log exception`() {
        // GIVEN
        val exception = IllegalStateException()
        coEvery { mockDao.insertAlbum(album = any()) } throws exception

        // WHEN
        val album = stubAlbum()
        runBlocking { localDataSource.saveAlbum(album) }

        // THEN
        verify { mockLogger.error(exception) }
    }
}
