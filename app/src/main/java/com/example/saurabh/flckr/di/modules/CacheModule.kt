package com.example.saurabh.flckr.di.modules

import android.app.Application
import com.example.saurabh.flckr.data.remote.models.ImagesCache
import com.example.saurabh.flckr.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CacheModule(private val application: Application) {

    @ApplicationScope
    @Provides
    fun provideImagesCache(): ImagesCache = ImagesCache()
}
