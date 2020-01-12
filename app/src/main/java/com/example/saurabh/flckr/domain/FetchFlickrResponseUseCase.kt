package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.models.FlickrResponse
import com.example.saurabh.flckr.models.RequestQueryParams
import io.reactivex.Single
import javax.inject.Inject


class FetchFlickrResponseUseCase @Inject constructor(private val dataRepository: DataRepository) :
    UseCase<Unit, Single<FlickrResponse>>() {
    override fun execute(parameters: Unit): Single<FlickrResponse> {
        val requestQueryParams = RequestQueryParams()
        return dataRepository.getFlickrResponse(requestQueryParams)
    }
}