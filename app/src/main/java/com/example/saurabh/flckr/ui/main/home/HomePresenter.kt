package com.example.saurabh.flckr.ui.main.home

import androidx.annotation.VisibleForTesting
import com.example.saurabh.flckr.data.cache.models.ItemChange
import com.example.saurabh.flckr.domain.FetchFlickrResponseUseCase
import com.example.saurabh.flckr.domain.FetchImageCacheUpdateUseCase
import com.example.saurabh.flckr.domain.FetchImagesFromResponseUseCase
import com.example.saurabh.flckr.domain.SaveImagesToCacheUseCase
import com.example.saurabh.flckr.extensions.applySchedulers
import com.example.saurabh.flckr.ui.base.BasePresenter
import com.example.saurabh.flckr.ui.main.home.HomeContract
import javax.inject.Inject


class HomePresenter @Inject constructor(
    private val fetchFlickrResponseUseCase: FetchFlickrResponseUseCase,
    private val fetchImagesFromResponseUseCase: FetchImagesFromResponseUseCase,
    private val saveImagesToCacheUseCase: SaveImagesToCacheUseCase,
    private val fetchImageCacheUpdateUseCase: FetchImageCacheUpdateUseCase
) :
    BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {
    private var count: Int = 0

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
        initFlickrRequest()
        listenToImageCache()
    }

    @VisibleForTesting
    fun initFlickrRequest() {
        disposables?.add(fetchFlickrResponseUseCase.execute(Unit)
            .map { it.photos?.photo }
            .flattenAsObservable { v -> v }
            .flatMapSingle { fetchImagesFromResponseUseCase.execute(it) }
            .map { saveImagesToCacheUseCase.execute(it) }
            .applySchedulers()
            .subscribe({}, {})
        )
    }

    @VisibleForTesting
    fun listenToImageCache() {
        disposables?.add(
            fetchImageCacheUpdateUseCase.execute(Unit)
                .applySchedulers()
                .subscribe({
                    when (it.change) {
                        ItemChange.ITEM_ADD -> {
                            if (it.item.label == "Thumbnail" && count < 9) {
                                getView()?.updateList(it.item, it.position)
                                count++
                            }
                        }
                    }
                }, {
                    println(it)
                })
        )
    }
}