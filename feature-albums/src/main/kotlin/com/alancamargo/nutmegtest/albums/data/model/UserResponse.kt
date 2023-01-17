package com.alancamargo.nutmegtest.albums.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserResponse(
    @SerialName("id") val id: Long,
    @SerialName("username") val userName: String
)
