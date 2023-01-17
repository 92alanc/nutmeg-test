package com.alancamargo.nutmegtest.albums.data.service

import com.alancamargo.nutmegtest.albums.data.model.response.AlbumResponse
import com.alancamargo.nutmegtest.albums.data.model.response.PhotoResponse
import com.alancamargo.nutmegtest.albums.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface AlbumService {

    @GET("albums")
    suspend fun getAlbums(): Response<List<AlbumResponse>>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId: Long): Response<List<PhotoResponse>>

    @GET("users/{user_id}")
    suspend fun getUser(@Path("user_id") userId: Long): Response<UserResponse>
}
