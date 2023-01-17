package com.alancamargo.nutmegtest.albums.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PhotoResponse(
    @SerialName("id") val id: Long,
    @SerialName("albumId") val albumId: Long,
    @SerialName("thumbnailUrl") val thumbnailUrl: String
)
