package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.data.cache.models.ItemChange
import com.example.saurabh.flckr.models.PhotoSizes
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FetchImageCacheUpdateUseCase @Inject constructor(private val dataRepository: DataRepository) :
    UseCase<Unit, PublishSubject<ItemChange<PhotoSizes>>>() {
    override fun execute(parameters: Unit): PublishSubject<ItemChange<PhotoSizes>> {
        return dataRepository.getPhotoSizesUpdates()
    }
}