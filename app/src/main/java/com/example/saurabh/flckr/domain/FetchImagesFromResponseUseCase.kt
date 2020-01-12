package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.models.FlickrSizeResponse
import com.example.saurabh.flckr.models.Photo
import com.example.saurabh.flckr.models.RequestSizeQueryParams
import io.reactivex.Single
import javax.inject.Inject

class FetchImagesFromResponseUseCase @Inject constructor(private val dataRepository: DataRepository) :
    UseCase<Photo, Single<Pair<FlickrSizeResponse, String>>>() {
    override fun execute(parameters: Photo): Single<Pair<FlickrSizeResponse, String>> =
        Single.create {
            try {
                val requestSizeQueryParams = RequestSizeQueryParams()
                requestSizeQueryParams.photoId = parameters.id ?: ""
                val sizes = dataRepository.getPhotoSizes(requestSizeQueryParams)
                it.onSuccess(Pair(sizes.blockingGet(), requestSizeQueryParams.photoId))
            } catch (e: Exception) {
                it.onError(e)
            }
        }
}