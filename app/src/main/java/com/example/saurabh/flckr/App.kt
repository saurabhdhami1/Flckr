package com.example.saurabh.flckr

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.saurabh.flckr.di.modules.ApplicationModule
import com.example.saurabh.flckr.di.modules.CacheModule
import com.example.saurabh.flckr.di.modules.NetworkModule
import com.example.saurabh.flckr.di.components.ApplicationComponent
import com.example.saurabh.flckr.di.components.DaggerApplicationComponent
import com.facebook.stetho.Stetho

class App : Application(), LifecycleObserver {

    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .cacheModule(CacheModule(this))
            .networkModule(NetworkModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }
}