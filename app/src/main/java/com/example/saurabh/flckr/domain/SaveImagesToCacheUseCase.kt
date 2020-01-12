package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.models.FlickrSizeResponse
import javax.inject.Inject

class SaveImagesToCacheUseCase @Inject constructor(private val dataRepository: DataRepository) :
    UseCase<Pair<FlickrSizeResponse, String>, Unit>() {
    override fun execute(parameters: Pair<FlickrSizeResponse, String>) {
        val sizeResponse = parameters.first
        val photoId = parameters.second.toLong()
        sizeResponse.sizes?.size?.forEach {
            val photoSizes = dataRepository.getSizeByLabelAndId(it.label ?: "", photoId)
            if (photoSizes == null){
                it.photoId = photoId
                dataRepository.storeImages(it)
            }
        }
    }
}