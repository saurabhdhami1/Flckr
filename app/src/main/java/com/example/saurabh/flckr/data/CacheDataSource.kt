package com.example.saurabh.flckr.data

import com.example.saurabh.flckr.data.remote.models.ImagesCache
import com.example.saurabh.flckr.models.ApplicationState
import com.example.saurabh.flckr.models.PhotoSizes
import javax.inject.Inject

open class CacheDataSource @Inject constructor(
    private val applicationState: ApplicationState,
    private val imagesCache: ImagesCache
) {

    fun storeImages(photoSizes: PhotoSizes) = imagesCache.put(photoSizes)

    fun getImageSizes() = imagesCache.get()

    fun getImagesByLabelAndPhotoId(label:String,photoId:Long) = imagesCache.findSizeByLabel(label,photoId)

    fun getPhotoSizeUpdates() = imagesCache.getUpdates()
    fun clearCache() {
        applicationState.clearState()
    }

    fun getImagesByLabel(label: String) = imagesCache.getImagesByLabel(label)
}