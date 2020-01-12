package com.example.saurabh.flckr.data.remote.services

import com.example.saurabh.flckr.models.FlickrResponse
import com.example.saurabh.flckr.models.FlickrSizeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface DeviceApiService {
    @POST("services/rest/")
    fun getFlickrResponse(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("user_id") userId: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: String
    ): Single<FlickrResponse>

    @GET("services/rest/")
    fun getPhotoSizes(
        @Query("method") method: String,
        @Query("photo_id") photoId: String,
        @Query("api_key") apiKey: String,
        @Query("user_id") userId: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: String
    ): Single<FlickrSizeResponse>
}