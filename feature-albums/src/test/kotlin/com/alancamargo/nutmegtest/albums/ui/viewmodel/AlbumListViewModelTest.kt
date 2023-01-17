package com.alancamargo.nutmegtest.albums.ui.viewmodel

import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.alancamargo.nutmegtest.albums.domain.usecase.GetAlbumsUseCase
import com.alancamargo.nutmegtest.albums.testtools.stubAlbumList
import com.alancamargo.nutmegtest.albums.ui.model.AlbumListError
import com.alancamargo.nutmegtest.core.log.Logger
import com.alancamargo.nutmegtest.core.test.ViewModelFlowCollector
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumListViewModelTest {

    private val mockGetAlbumsUseCase = mockk<GetAlbumsUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    private val viewModel = AlbumListViewModel(
        mockGetAlbumsUseCase,
        mockLogger,
        testDispatcher
    )

    private val collector = ViewModelFlowCollector(
        viewModel.state,
        viewModel.action,
        testDispatcher
    )

    @Test
    fun `when not on first launch onCreate should log event`() {
        // WHEN
        viewModel.onCreate(isFirstLaunch = false)

        // THEN
        verify { mockLogger.debug("onCreate called. First launch: false") }
    }

    @Test
    fun `when not on first launch onCreate should not get albums`() {
        // WHEN
        viewModel.onCreate(isFirstLaunch = false)

        // THEN
        verify(exactly = 0) { mockGetAlbumsUseCase() }
    }

    @Test
    fun `when on first launch onCreate should log event`() {
        // GIVEN
        every { mockGetAlbumsUseCase() } returns flowOf(AlbumListResult.Empty)

        // WHEN
        viewModel.onCreate(isFirstLaunch = true)

        // THEN
        verify { mockLogger.debug("onCreate called. First launch: true") }
    }

    @Test
    fun `when use case returns Success onCreate should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            val albums = stubAlbumList()
            every { mockGetAlbumsUseCase() } returns flowOf(AlbumListResult.Success(albums))

            // WHEN
            viewModel.onCreate(isFirstLaunch = true)

            // THEN
            val expected = listOf(
                AlbumListViewState(isLoading = true),
                AlbumListViewState(isLoading = false, albums = albums)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case returns Empty onCreate should send ShowErrorDialogue action with EMPTY error`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockGetAlbumsUseCase() } returns flowOf(AlbumListResult.Empty)

            // WHEN
            viewModel.onCreate(isFirstLaunch = true)

            // THEN
            val expected = AlbumListViewAction.ShowErrorDialogue(AlbumListError.NO_RESULTS)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when use case returns NetworkError onCreate should send ShowErrorDialogue action with DISCONNECTED error`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockGetAlbumsUseCase() } returns flowOf(AlbumListResult.NetworkError)

            // WHEN
            viewModel.onCreate(isFirstLaunch = true)

            // THEN
            val expected = AlbumListViewAction.ShowErrorDialogue(AlbumListError.DISCONNECTED)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when use case returns ServerError onCreate should send ShowErrorDialogue action with SERVER error`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockGetAlbumsUseCase() } returns flowOf(AlbumListResult.ServerError)

            // WHEN
            viewModel.onCreate(isFirstLaunch = true)

            // THEN
            val expected = AlbumListViewAction.ShowErrorDialogue(AlbumListError.SERVER)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when use case returns GenericError onCreate should send ShowErrorDialogue action with GENERIC error`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockGetAlbumsUseCase() } returns flowOf(AlbumListResult.GenericError)

            // WHEN
            viewModel.onCreate(isFirstLaunch = true)

            // THEN
            val expected = AlbumListViewAction.ShowErrorDialogue(AlbumListError.GENERIC)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when use case returns exception onCreate should send ShowErrorDialogue action with GENERIC error`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockGetAlbumsUseCase() } returns flow { throw Throwable() }

            // WHEN
            viewModel.onCreate(isFirstLaunch = true)

            // THEN
            val expected = AlbumListViewAction.ShowErrorDialogue(AlbumListError.GENERIC)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when use case returns exception onCreate should log exception`() {
        // GIVEN
        val exception = Throwable("Something is wrong, obviously")
        every { mockGetAlbumsUseCase() } returns flow { throw exception }

        // WHEN
        viewModel.onCreate(isFirstLaunch = true)

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `onTryAgainClicked should log event`() {
        // GIVEN
        every { mockGetAlbumsUseCase() } returns flow { throw Throwable() }

        // WHEN
        viewModel.onTryAgainClicked()

        // THEN
        verify { mockLogger.debug("Try again clicked") }
    }

    @Test
    fun `onTryAgainClicked should get albums`() {
        // GIVEN
        every { mockGetAlbumsUseCase() } returns flow { throw Throwable() }

        // WHEN
        viewModel.onTryAgainClicked()

        // THEN
        verify { mockGetAlbumsUseCase() }
    }

    @Test
    fun `onAppInfoClicked should log event`() {
        // WHEN
        viewModel.onAppInfoClicked()

        // THEN
        verify { mockLogger.debug("App info button clicked") }
    }

    @Test
    fun `onAppInfoClicked should send ShowAppInfo action`() {
        collector.test { _, actions ->
            // WHEN
            viewModel.onAppInfoClicked()

            // THEN
            val expected = AlbumListViewAction.ShowAppInfo
            assertThat(actions).contains(expected)
        }
    }
}
