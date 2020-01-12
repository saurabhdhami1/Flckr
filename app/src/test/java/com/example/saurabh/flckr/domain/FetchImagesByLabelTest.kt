package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.BasePresenterTest
import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.models.PhotoSizes
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class FetchImagesByLabelTest : BasePresenterTest() {

    @Mock
    lateinit var dataRepository: DataRepository

    private lateinit var fetchImagesByLabel: FetchImagesByLabel

    @Before
    override fun setUp() {
        super.setUp()
        fetchImagesByLabel = FetchImagesByLabel(dataRepository)
    }

    @Test
    fun execute_fetchImagesByLabel_shouldFetchListOfImages() {
        //given

        val parameters = "Thumbnail"
        val list = listOf<PhotoSizes>(PhotoSizes(label = "Thumbnail"))
        //when
        whenever(fetchImagesByLabel.execute(parameters)).thenReturn(list)
        fetchImagesByLabel.execute(parameters)
        //then
        verify(dataRepository).getImagesByLabel(parameters)
    }
}