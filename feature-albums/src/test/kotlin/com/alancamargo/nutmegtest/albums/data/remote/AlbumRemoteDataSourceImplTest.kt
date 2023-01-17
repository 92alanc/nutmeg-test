package com.alancamargo.nutmegtest.albums.data.remote

import com.alancamargo.nutmegtest.albums.data.service.AlbumService
import com.alancamargo.nutmegtest.albums.testtools.stubAlbumList
import com.alancamargo.nutmegtest.albums.testtools.stubAlbumResponseList
import com.alancamargo.nutmegtest.albums.testtools.stubPhotoResponseList
import com.alancamargo.nutmegtest.albums.testtools.stubUserResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AlbumRemoteDataSourceImplTest {

    private val mockService = mockk<AlbumService>()
    private val remoteDataSource = AlbumRemoteDataSourceImpl(mockService)

    @Test
    fun `getAlbums should return albums from service as domain`() {
        // GIVEN
        coEvery { mockService.getAlbums() } returns stubAlbumResponseList()
        coEvery { mockService.getUser(userId = 1) } returns stubUserResponse()
        coEvery { mockService.getPhotos(albumId = any()) } returns stubPhotoResponseList()

        // WHEN
        val actual = runBlocking { remoteDataSource.getAlbums() }

        // THEN
        val expected = stubAlbumList()
        assertThat(actual).isEqualTo(expected)
    }
}
