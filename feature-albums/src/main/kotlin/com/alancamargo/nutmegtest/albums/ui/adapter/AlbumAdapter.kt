package com.alancamargo.nutmegtest.albums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.nutmegtest.albums.databinding.ItemAlbumBinding
import com.alancamargo.nutmegtest.albums.domain.model.Album

internal class AlbumAdapter : ListAdapter<Album, AlbumViewHolder>(AlbumDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumBinding.inflate(inflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bindTo(album)
    }
}
