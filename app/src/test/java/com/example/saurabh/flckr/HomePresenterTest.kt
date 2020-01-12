package com.example.saurabh.flckr

import com.example.saurabh.flckr.data.cache.models.ItemChange
import com.example.saurabh.flckr.domain.FetchFlickrResponseUseCase
import com.example.saurabh.flckr.domain.FetchImageCacheUpdateUseCase
import com.example.saurabh.flckr.domain.FetchImagesFromResponseUseCase
import com.example.saurabh.flckr.domain.SaveImagesToCacheUseCase
import com.example.saurabh.flckr.models.PhotoSizes
import com.example.saurabh.flckr.ui.main.home.HomeContract
import com.example.saurabh.flckr.ui.main.home.HomePresenter
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class HomePresenterTest : BasePresenterTest() {

    @Mock
    lateinit var fetchFlickrResponseUseCase: FetchFlickrResponseUseCase
    @Mock
    lateinit var fetchImagesFromResponseUseCase: FetchImagesFromResponseUseCase
    @Mock
    lateinit var saveImagesToCacheUseCase: SaveImagesToCacheUseCase
    @Mock
    lateinit var fetchImageCacheUpdateUseCase: FetchImageCacheUpdateUseCase

    @Mock
    lateinit var view: HomeContract.View

    private lateinit var homePresenter: HomePresenter

    @Before
    override fun setUp() {
        super.setUp()
        homePresenter = HomePresenter(
            fetchFlickrResponseUseCase,
            fetchImagesFromResponseUseCase,
            saveImagesToCacheUseCase,
            fetchImageCacheUpdateUseCase
        )
        homePresenter.attachView(view)
    }

    @Test
    fun listenToImageCache_shouldUpdateList_whenNewItemsAreAddedToCache() {
        //given
        val item = PhotoSizes(label = "Thumbnail")
        val itemChange = ItemChange(ItemChange.ITEM_ADD, 0, item)
        val subject = PublishSubject.create<ItemChange<PhotoSizes>>()
        //when
        whenever(fetchImageCacheUpdateUseCase.execute(Unit)).thenReturn(subject)
        homePresenter.listenToImageCache()
        subject.onNext(itemChange)
        //then
        then(view).should().updateList(item, 0)
    }

}