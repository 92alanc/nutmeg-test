package com.alancamargo.nutmegtest.albums.data.repository

import com.alancamargo.nutmegtest.albums.data.local.AlbumLocalDataSource
import com.alancamargo.nutmegtest.albums.data.model.response.AlbumResponse
import com.alancamargo.nutmegtest.albums.data.remote.AlbumRemoteDataSource
import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.alancamargo.nutmegtest.albums.testtools.stubAlbumList
import com.alancamargo.nutmegtest.core.log.Logger
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class AlbumRepositoryImplTest {

    private val mockRemoteDataSource = mockk<AlbumRemoteDataSource>()
    private val mockLocalDataSource = mockk<AlbumLocalDataSource>(relaxed = true)
    private val mockLogger = mockk<Logger>(relaxed = true)

    private val repository = AlbumRepositoryImpl(
        mockRemoteDataSource,
        mockLocalDataSource,
        mockLogger
    )

    @Test
    fun `when remote returns albums getAlbums should return Success`() {
        // GIVEN
        val albums = stubAlbumList()
        coEvery { mockRemoteDataSource.getAlbums() } returns albums

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.Success(albums)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote returns empty list getAlbums should return Empty`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getAlbums() } returns emptyList()

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.Empty
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote returns albums getAlbums should not call local`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getAlbums() } returns stubAlbumList()

        // WHEN
        runBlocking { repository.getAlbums() }

        // THEN
        coVerify(exactly = 0) { mockLocalDataSource.getAlbums() }
    }

    @Test
    fun `when remote returns albums getAlbums should save albums in local`() {
        // GIVEN
        val expected = stubAlbumList()
        coEvery { mockRemoteDataSource.getAlbums() } returns expected

        // WHEN
        runBlocking { repository.getAlbums() }

        // THEN
        coVerify(exactly = expected.size) {
            mockLocalDataSource.saveAlbum(album = any())
        }
    }

    @Test
    fun `when remote returns empty list getAlbums should not save albums in local`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getAlbums() } returns emptyList()

        // WHEN
        runBlocking { repository.getAlbums() }

        // THEN
        coVerify(exactly = 0) { mockLocalDataSource.saveAlbum(album = any()) }
    }

    @Test
    fun `when remote throws exception getAlbums should log exception`() {
        // GIVEN
        val exception = IllegalArgumentException()
        coEvery { mockRemoteDataSource.getAlbums() } throws exception
        coEvery { mockLocalDataSource.getAlbums() } returns stubAlbumList()

        // WHEN
        runBlocking { repository.getAlbums() }

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when remote throws exception and local returns albums getAlbums should return Success`() {
        // GIVEN
        val albums = stubAlbumList()
        coEvery { mockRemoteDataSource.getAlbums() } throws IllegalArgumentException()
        coEvery { mockLocalDataSource.getAlbums() } returns albums

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.Success(albums)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote throws exception and local returns empty list getAlbums should return Empty`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getAlbums() } throws IllegalArgumentException()
        coEvery { mockLocalDataSource.getAlbums() } returns emptyList()

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.Empty
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when both remote and local throw exception getAlbums should log local exception`() {
        // GIVEN
        val exception = IllegalStateException()
        coEvery { mockRemoteDataSource.getAlbums() } throws IOException()
        coEvery { mockLocalDataSource.getAlbums() } throws exception

        // WHEN
        runBlocking { repository.getAlbums() }

        // THEN
        every { mockLogger.error(exception) }
    }

    @Test
    fun `when remote throws IOException and local fails getAlbums should return NetworkError`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getAlbums() } throws IOException()
        coEvery { mockLocalDataSource.getAlbums() } throws Throwable()

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.NetworkError
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote throws HttpException with server error and local fails getAlbums should return ServerError`() {
        // GIVEN
        val response = Response.error<List<AlbumResponse>>(500, "".toResponseBody())
        coEvery { mockRemoteDataSource.getAlbums() } throws HttpException(response)
        coEvery { mockLocalDataSource.getAlbums() } throws Throwable()

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.ServerError
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote throws HttpException with generic error and local fails getAlbums should return GenericError`() {
        // GIVEN
        val response = Response.error<List<AlbumResponse>>(404, "".toResponseBody())
        coEvery { mockRemoteDataSource.getAlbums() } throws HttpException(response)
        coEvery { mockLocalDataSource.getAlbums() } throws Throwable()

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.GenericError
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when remote throws generic exception and local fails getAlbums should return GenericError`() {
        // GIVEN
        coEvery { mockRemoteDataSource.getAlbums() } throws IllegalArgumentException()
        coEvery { mockLocalDataSource.getAlbums() } throws Throwable()

        // WHEN
        val actual = runBlocking { repository.getAlbums() }

        // THEN
        val expected = AlbumListResult.GenericError
        assertThat(actual).isEqualTo(expected)
    }
}
