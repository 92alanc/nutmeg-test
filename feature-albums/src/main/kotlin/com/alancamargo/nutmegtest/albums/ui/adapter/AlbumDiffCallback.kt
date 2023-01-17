package com.alancamargo.nutmegtest.albums.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.nutmegtest.albums.domain.model.Album

internal object AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}
