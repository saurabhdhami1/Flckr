package com.example.saurabh.flckr.ui.main.home

import com.example.saurabh.flckr.models.PhotoSizes
import com.example.saurabh.flckr.ui.base.BaseContract

interface HomeContract {

    interface View : BaseContract.View {

        fun initUI()
        fun updateList(item: PhotoSizes, position: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}