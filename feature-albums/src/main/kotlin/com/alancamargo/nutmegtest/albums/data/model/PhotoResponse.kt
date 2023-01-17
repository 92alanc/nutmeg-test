package com.alancamargo.nutmegtest.albums.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PhotoResponse(
    @SerialName("id") val id: Long,
    @SerialName("albumId") val albumId: Long,
    @SerialName("title") val title: String,
    @SerialName("url") val url: String,
    @SerialName("thumbnailUrl") val thumbnailUrl: String
)
