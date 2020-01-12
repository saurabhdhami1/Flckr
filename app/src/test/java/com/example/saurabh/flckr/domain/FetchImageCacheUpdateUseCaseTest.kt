package com.example.saurabh.flckr.domain

import com.example.saurabh.flckr.BasePresenterTest
import com.example.saurabh.flckr.data.DataRepository
import com.example.saurabh.flckr.data.cache.models.ItemChange
import com.example.saurabh.flckr.models.PhotoSizes
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class FetchImageCacheUpdateUseCaseTest : BasePresenterTest() {

    @Mock
    lateinit var dataRepository: DataRepository

    private lateinit var fetchImageCacheUpdateUseCase: FetchImageCacheUpdateUseCase

    @Before
    override fun setUp() {
        super.setUp()
        fetchImageCacheUpdateUseCase = FetchImageCacheUpdateUseCase(dataRepository)
    }

    @Test
    fun execute_shouldFetchImagesFromCache() {
        //given
        val item = PhotoSizes()
        val itemChange = ItemChange<PhotoSizes>(ItemChange.ITEM_ADD, 0, item)
        val publishSubject = PublishSubject.create<ItemChange<PhotoSizes>>()
        //when
        whenever(dataRepository.getPhotoSizesUpdates()).thenReturn(publishSubject)
        fetchImageCacheUpdateUseCase.execute(Unit)
        publishSubject.onNext(itemChange)
        //then
        verify(dataRepository).getPhotoSizesUpdates()
    }
}