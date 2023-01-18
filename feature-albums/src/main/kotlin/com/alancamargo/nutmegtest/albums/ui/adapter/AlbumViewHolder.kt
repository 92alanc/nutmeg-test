package com.alancamargo.nutmegtest.albums.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.alancamargo.nutmegtest.albums.databinding.ItemAlbumBinding
import com.alancamargo.nutmegtest.albums.domain.model.Album
import com.alancamargo.nutmegtest.core.design.R

internal class AlbumViewHolder(
    private val binding: ItemAlbumBinding
) : ViewHolder(binding.root) {

    fun bindTo(album: Album) = with(binding) {
        imgThumbnail.load(album.thumbnailUrl) {
            placeholder(R.drawable.ic_album_thumbnail_placeholder)
        }
        txtTitle.text = album.title
        txtUserName.text = album.userName
    }
}
