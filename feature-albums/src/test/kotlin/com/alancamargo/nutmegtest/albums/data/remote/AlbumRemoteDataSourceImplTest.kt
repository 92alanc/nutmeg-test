package com.alancamargo.nutmegtest.albums.data.remote

import com.alancamargo.nutmegtest.albums.data.model.response.AlbumResponse
import com.alancamargo.nutmegtest.albums.data.model.response.PhotoResponse
import com.alancamargo.nutmegtest.albums.data.model.response.UserResponse
import com.alancamargo.nutmegtest.albums.data.service.AlbumService
import com.alancamargo.nutmegtest.albums.domain.model.Album
import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val THUMBNAIL_URL = "https://piratebay.test/photo.jpg"

class AlbumRemoteDataSourceImplTest {

    private val mockService = mockk<AlbumService>()
    private val remoteDataSource = AlbumRemoteDataSourceImpl(mockService)

    @Test
    fun `when service succeeds getAlbums should return albums as domain`() {
        // GIVEN
        coEvery { mockService.getAlbums() } returns stubAlbumResponseList()
        coEvery { mockService.getUser(userId = 1) } returns stubUserResponse()
        coEvery { mockService.getPhotos(albumId = any()) } returns stubPhotoResponseList()

        // WHEN
        val actual = runBlocking { remoteDataSource.getAlbums() }

        // THEN
        val albums = listOf(
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
        val expected = AlbumListResult.Success(albums)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
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
    }

    private fun stubAlbumResponseList() = listOf(
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

    private fun stubUserResponse() = UserResponse(id = 1, userName = "user1")

    private fun stubPhotoResponseList() = listOf(
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
}
