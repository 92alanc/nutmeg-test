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

    /*@Test
    fun `when service returns empty list getAlbums should return Empty`() {
        // GIVEN
        coEvery { mockService.getAlbums() } returns emptyList()

        // WHEN
        val actual = runBlocking { remoteDataSource.getAlbums() }

        // THEN
        val expected = AlbumListResult.Empty
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when service throws HttpException with server error getAlbums should return ServerError`() {
        // GIVEN
        val response = Response.error<List<AlbumResponse>>(500, "".toResponseBody())
        coEvery { mockService.getAlbums() } throws HttpException(response)

        // WHEN
        val actual = runBlocking { remoteDataSource.getAlbums() }

        // THEN
        val expected = AlbumListResult.ServerError
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when service throws HttpException with generic error getAlbums should return GenericError`() {
        // GIVEN
        val response = Response.error<List<AlbumResponse>>(404, "".toResponseBody())
        coEvery { mockService.getAlbums() } throws HttpException(response)

        // WHEN
        val actual = runBlocking { remoteDataSource.getAlbums() }

        // THEN
        val expected = AlbumListResult.GenericError
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when service throws IOException getAlbums should return NetworkError`() {
        // GIVEN
        coEvery { mockService.getAlbums() } throws IOException()

        // WHEN
        val actual = runBlocking { remoteDataSource.getAlbums() }

        // THEN
        val expected = AlbumListResult.NetworkError
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when service throws generic exception getAlbums should return GenericError`() {
        // GIVEN
        coEvery { mockService.getAlbums() } throws Throwable()

        // WHEN
        val actual = runBlocking { remoteDataSource.getAlbums() }

        // THEN
        val expected = AlbumListResult.GenericError
        assertThat(actual).isEqualTo(expected)
    }*/
}
