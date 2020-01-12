package com.example.saurabh.flckr.ui.base

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.saurabh.flckr.App
import com.example.saurabh.flckr.R

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : AppCompatActivity(),
    BaseContract.View {

    protected var presenter: P? = null
    protected var hasSavedInstance: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hasSavedInstance = savedInstanceState != null //restored for e.g., after orientation
        setContentView(getLayoutResId())

        val isPortraitOnly = resources.getBoolean(R.bool.isPortraitOnly)
        requestedOrientation = if (isPortraitOnly) SCREEN_ORIENTATION_PORTRAIT else SCREEN_ORIENTATION_UNSPECIFIED

        injectDependencies()

        val viewModel: BaseViewModel<V, P> = ViewModelProviders.of(this).get(BaseViewModel<V, P>()::class.java)
        val isCreated = viewModel.presenter == null
        viewModel.presenter = viewModel.presenter ?: initPresenter()
        presenter = viewModel.presenter
        presenter?.attachView(this as V)
        presenter?.onSavedInstance(hasSavedInstance)
        if (isCreated) presenter?.onPresenterCreated()
        presenter?.onCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStarted()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResumed()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPaused()
    }

    override fun handleLongPress(): Boolean {
        return false
    }

    fun getApplicationComponent() = (application as App).applicationComponent

    protected abstract fun initPresenter(): P

    protected abstract fun injectDependencies()

    protected abstract fun getLayoutResId(): Int
}