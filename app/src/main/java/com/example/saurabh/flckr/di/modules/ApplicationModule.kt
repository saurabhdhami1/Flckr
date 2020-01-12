package com.example.saurabh.flckr.di.modules

import android.app.Application
import android.content.Context
import com.example.saurabh.flckr.models.ApplicationState
import com.example.saurabh.flckr.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @ApplicationScope
    @Provides
    fun providesApplicationContext(): Context = application.applicationContext

    @ApplicationScope
    @Provides
    fun providesApplicationScope() = ApplicationState()
}
