package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.BasePresenterTest
import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.models.FlickrResponse
import com.example.saurabh.flckr.models.RequestQueryParams
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class FetchFlickrResponseUseCaseTest : BasePresenterTest() {

    @Mock
    lateinit var dataRepository: DataRepository

    private lateinit var fetchFlickrResponseUseCase: FetchFlickrResponseUseCase

    @Before
    override fun setUp() {
        super.setUp()
        fetchFlickrResponseUseCase = FetchFlickrResponseUseCase(dataRepository)
    }

    @Test
    fun execute_shouldReturnFlckrResponse() {
        val flickrResponse = FlickrResponse()
        val requestQueryParams = RequestQueryParams()
        val single = Single.create<FlickrResponse> { emitter ->
            emitter.onSuccess(flickrResponse)
        }
        //when
        whenever(dataRepository.getFlickrResponse(requestQueryParams)).thenReturn(single)
        fetchFlickrResponseUseCase.execute(Unit)
        //then
        verify(dataRepository).getFlickrResponse(requestQueryParams)

    }
}