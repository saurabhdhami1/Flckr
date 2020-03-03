package com.example.saurabh.flckr.data

import com.example.saurabh.flckr.data.remote.RemoteDataSource
import com.example.saurabh.flckr.models.PhotoSizes
import com.example.saurabh.flckr.models.RequestQueryParams
import com.example.saurabh.flckr.models.RequestSizeQueryParams
import javax.inject.Inject

open class DataRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val cacheDataSource: CacheDataSource
) {

    fun getFlickrResponse(requestQueryParams: RequestQueryParams) =
        remoteDataSource.getFlickrResponse(requestQueryParams)

    fun getPhotoSizes(queryParams: RequestSizeQueryParams) =
        remoteDataSource.getPhotoSizes(queryParams)

    fun getImages() = cacheDataSource.getImageSizes()
    fun storeImages(photoSizes: PhotoSizes) = cacheDataSource.storeImages(photoSizes)
    fun getSizeByLabelAndId(label: String, photoId: Long) =
        cacheDataSource.getImagesByLabelAndPhotoId(label, photoId)

    fun getImagesByLabel(label: String) = cacheDataSource.getImagesByLabel(label)
    fun getPhotoSizesUpdates() = cacheDataSource.getPhotoSizeUpdates()
    fun saurabh() = cacheDataSource.getPhotoSizeUpdates()

}
