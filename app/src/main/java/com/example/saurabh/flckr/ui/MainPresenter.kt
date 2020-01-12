package com.example.saurabh.flckr.ui

import com.example.saurabh.flckr.ui.MainContract
import com.example.saurabh.flckr.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(): BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
    }

    override fun onDestroyed() {
        super.onDestroyed()
        disposables?.clear()
    }
}