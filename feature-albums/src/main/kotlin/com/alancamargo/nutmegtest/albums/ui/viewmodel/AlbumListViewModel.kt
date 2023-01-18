package com.alancamargo.nutmegtest.albums.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.nutmegtest.albums.domain.model.AlbumListResult
import com.alancamargo.nutmegtest.albums.domain.usecase.GetAlbumsUseCase
import com.alancamargo.nutmegtest.albums.ui.model.AlbumListError
import com.alancamargo.nutmegtest.core.di.IoDispatcher
import com.alancamargo.nutmegtest.core.log.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AlbumListViewModel @Inject constructor(
    private val getAlbums: GetAlbumsUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(AlbumListViewState())
    private val _action = MutableSharedFlow<AlbumListViewAction>()

    val state: StateFlow<AlbumListViewState> = _state
    val action: SharedFlow<AlbumListViewAction> = _action

    fun onCreate(isFirstLaunch: Boolean) {
        logger.debug("onCreate called. First launch: $isFirstLaunch")

        if (isFirstLaunch) {
            fetchAlbums()
        }
    }

    fun onTryAgainClicked() {
        logger.debug("Try again clicked")
        fetchAlbums()
    }

    fun onAppInfoClicked() {
        logger.debug("App info button clicked")

        viewModelScope.launch(dispatcher) {
            _action.emit(AlbumListViewAction.ShowAppInfo)
        }
    }

    private fun fetchAlbums() {
        viewModelScope.launch(dispatcher) {
            getAlbums().onStart {
                _state.update { it.onLoading() }
            }.catch { t ->
                handleUnexpectedException(t)
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.collect(::handleResult)
        }
    }

    private suspend fun handleUnexpectedException(t: Throwable) {
        logger.error(t)
        val error = AlbumListError.GENERIC
        _action.emit(AlbumListViewAction.ShowErrorDialogue(error))
    }

    private suspend fun handleResult(result: AlbumListResult) {
        when (result) {
            is AlbumListResult.Success -> _state.update { it.onAlbumsReceived(result.albums) }

            is AlbumListResult.Empty -> {
                val error = AlbumListError.NO_RESULTS
                _action.emit(AlbumListViewAction.ShowErrorDialogue(error))
            }

            is AlbumListResult.ServerError -> {
                val error = AlbumListError.SERVER
                _action.emit(AlbumListViewAction.ShowErrorDialogue(error))
            }

            is AlbumListResult.NetworkError -> {
                val error = AlbumListError.DISCONNECTED
                _action.emit(AlbumListViewAction.ShowErrorDialogue(error))
            }

            is AlbumListResult.GenericError -> {
                val error = AlbumListError.GENERIC
                _action.emit(AlbumListViewAction.ShowErrorDialogue(error))
            }
        }
    }
}
