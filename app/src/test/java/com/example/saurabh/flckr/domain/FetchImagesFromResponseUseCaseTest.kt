package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.BasePresenterTest
import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.models.FlickrSizeResponse
import com.example.saurabh.flckr.models.Photo
import com.example.saurabh.flckr.models.RequestSizeQueryParams
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class FetchImagesFromResponseUseCaseTest : BasePresenterTest() {

    @Mock
    lateinit var dataRepository: DataRepository

    private lateinit var fetchImagesFromResponseUseCase: FetchImagesFromResponseUseCase

    @Before
    override fun setUp() {
        super.setUp()
        fetchImagesFromResponseUseCase = FetchImagesFromResponseUseCase(dataRepository)
    }

    @Test
    fun execute_fetchImagesFromResponseUseCase_shouldFetchFlickrSizeResponse() {
        //given
        val photo = Photo(id = "12345")
        val flickrSizeResponse = FlickrSizeResponse()
        val requestQueryParams = RequestSizeQueryParams(photoId = photo.id ?: "")
        val pair = Pair(flickrSizeResponse, photo.id ?: "")
        val single = Single.create<FlickrSizeResponse> { emitter ->
            emitter.onSuccess(flickrSizeResponse)
        }
        //when
        whenever(dataRepository.getPhotoSizes(requestQueryParams)).thenReturn(single)
        fetchImagesFromResponseUseCase.execute(photo).blockingGet()
        //then
        verify(dataRepository).getPhotoSizes(requestQueryParams)
    }
}