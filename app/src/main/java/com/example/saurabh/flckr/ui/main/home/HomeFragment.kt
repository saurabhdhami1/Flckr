package com.example.saurabh.flckr.ui.main.home

import com.example.saurabh.flckr.models.PhotoSizes
import com.example.saurabh.flckr.ui.base.BaseFragment
import com.example.saurabh.flckr.R
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View {

    @Inject
    lateinit var homePresenter: HomePresenter
    lateinit var adapter: FlckrImagesAdapter

    override fun initUI() {
        adapter = FlckrImagesAdapter()
        flckrRecyView.adapter = adapter
    }

    override fun updateList(item: PhotoSizes, position: Int) {
        adapter.list.add(item)
        adapter.notifyItemChanged(position)
    }

    override fun initPresenter() = homePresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.fragment_home
}
