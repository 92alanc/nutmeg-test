package com.alancamargo.nutmegtest.albums.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.nutmegtest.albums.R
import com.alancamargo.nutmegtest.albums.databinding.ActivityAlbumListBinding
import com.alancamargo.nutmegtest.albums.ui.adapter.AlbumAdapter
import com.alancamargo.nutmegtest.albums.ui.viewmodel.AlbumListViewAction
import com.alancamargo.nutmegtest.albums.ui.viewmodel.AlbumListViewModel
import com.alancamargo.nutmegtest.albums.ui.viewmodel.AlbumListViewState
import com.alancamargo.nutmegtest.core.design.dialogue.DialogueHelper
import com.alancamargo.nutmegtest.core.extensions.observeViewModelFlow
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.alancamargo.nutmegtest.core.design.R as R2

@AndroidEntryPoint
class AlbumListActivity : AppCompatActivity() {

    private var _binding: ActivityAlbumListBinding? = null

    private val binding: ActivityAlbumListBinding
        get() = _binding!!

    private val viewModel by viewModels<AlbumListViewModel>()
    private val adapter = AlbumAdapter()

    @Inject
    lateinit var dialogueHelper: DialogueHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAlbumListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        observeViewStateAndActions()
        viewModel.onCreate(isFirstLaunch = savedInstanceState == null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_album_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemAppInfo -> {
                viewModel.onAppInfoClicked()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeViewStateAndActions() {
        observeViewModelFlow(viewModel.state, ::onStateChanged)
        observeViewModelFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: AlbumListViewState) = with(state) {
        binding.shimmerContainer.isVisible = isLoading
        albums?.let(adapter::submitList)
    }

    private fun onAction(action: AlbumListViewAction) {
        when (action) {
            is AlbumListViewAction.ShowErrorDialogue -> {
                dialogueHelper.showDialogue(
                    context = this,
                    iconRes = action.error.iconRes,
                    titleRes = R2.string.error,
                    messageRes = action.error.messageRes,
                    buttonTextRes = R2.string.try_again
                )
            }

            is AlbumListViewAction.ShowAppInfo -> {
                dialogueHelper.showDialogue(
                    context = this,
                    iconRes = R2.mipmap.ic_launcher_round,
                    titleRes = R2.string.app_name,
                    messageRes = R.string.app_info,
                    buttonTextRes = R2.string.ok
                )
            }
        }
    }
}
