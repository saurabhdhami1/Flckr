package com.example.saurabh.flckr.data.remote

import com.example.saurabh.flckr.data.remote.services.DeviceApiService
import com.example.saurabh.flckr.models.FlickrResponse
import com.example.saurabh.flckr.models.FlickrSizeResponse
import com.example.saurabh.flckr.models.RequestQueryParams
import com.example.saurabh.flckr.models.RequestSizeQueryParams
import io.reactivex.Single
import javax.inject.Inject

open class RemoteDataSource @Inject constructor(private val deviceApiService: DeviceApiService) {

    fun getFlickrResponse(requestQueryParams: RequestQueryParams): Single<FlickrResponse> {
        return deviceApiService.getFlickrResponse(
            method = requestQueryParams.method,
            apiKey = requestQueryParams.apiKey,
            format = requestQueryParams.format,
            userId = requestQueryParams.userId,
            callback = requestQueryParams.callback
        )
    }

    fun getPhotoSizes(queryParams: RequestSizeQueryParams) :Single<FlickrSizeResponse>{
        return deviceApiService.getPhotoSizes(
            method = queryParams.method,
            photoId = queryParams.photoId,
            apiKey = queryParams.apiKey,
            format = queryParams.format,
            userId = queryParams.userId,
            callback = queryParams.callback
        )
    }
}