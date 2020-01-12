package com.example.saurabh.flckr.ui

import com.example.saurabh.flckr.ui.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun initUI()
    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}