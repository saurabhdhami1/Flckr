package com.example.saurabh.flckr.ui

import android.os.Bundle
import com.example.saurabh.flckr.ui.base.BaseActivity
import com.example.saurabh.flckr.ui.main.home.HomeFragment
import com.example.saurabh.flckr.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            //load for first timer
        }
    }


    override fun initUI() {
        val fragment = HomeFragment()
        val fragments = supportFragmentManager.fragments
        if (fragments.contains(supportFragmentManager.findFragmentByTag("HomeFragment"))) {
            return
        }
        supportFragmentManager
            .beginTransaction()
            .add(mainContainer.id, fragment, "HomeFragment")
            .commit()

    }

    override fun initPresenter() = mainPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId(): Int = R.layout.activity_main

}
