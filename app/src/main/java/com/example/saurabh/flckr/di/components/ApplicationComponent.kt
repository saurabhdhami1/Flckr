package com.example.saurabh.flckr.di.components

import com.example.saurabh.flckr.di.modules.ApplicationModule
import com.example.saurabh.flckr.ui.MainActivity
import com.example.saurabh.flckr.di.modules.CacheModule
import com.example.saurabh.flckr.di.modules.NetworkModule
import com.example.saurabh.flckr.ui.main.home.HomeFragment
import com.example.saurabh.flckr.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, NetworkModule::class, CacheModule::class])
interface ApplicationComponent {


    fun inject(activity: MainActivity)
    fun inject(homeFragment: HomeFragment)

}
