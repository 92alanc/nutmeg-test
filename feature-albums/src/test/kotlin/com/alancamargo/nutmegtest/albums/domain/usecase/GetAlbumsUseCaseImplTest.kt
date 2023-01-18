package com.alancamargo.nutmegtest.albums.domain.usecase

import app.cash.turbine.test
import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.alancamargo.nutmegtest.albums.domain.repository.AlbumRepository
import com.alancamargo.nutmegtest.albums.testtools.stubAlbumList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class GetAlbumsUseCaseImplTest {

    private val mockRepository = mockk<AlbumRepository>()
    private val useCase = GetAlbumsUseCaseImpl(mockRepository)

    @Test
    fun `invoke should return result from repository as flow`() = runBlocking {
        // GIVEN
        val albums = stubAlbumList()
        val expected = AlbumListResult.Success(albums)
        coEvery { mockRepository.getAlbums() } returns expected

        // WHEN
        val result = useCase()

        // THEN
        result.test {
            val actual = awaitItem()
            assertThat(actual).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when repository throws exception invoke should return flow with exception`() = runBlocking {
        // GIVEN
        coEvery { mockRepository.getAlbums() } throws IOException()

        // WHEN
        val result = useCase()

        // THEN
        result.test {
            val actual = awaitError()
            assertThat(actual).isInstanceOf(IOException::class.java)
        }
    }
}
