package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.models.PhotoSizes
import javax.inject.Inject

class FetchImagesByLabel @Inject constructor(private val dataRepository: DataRepository) :
    UseCase<String, List<PhotoSizes>>() {
    override fun execute(parameters: String): List<PhotoSizes> {
        return dataRepository.getImagesByLabel(parameters)
    }
}