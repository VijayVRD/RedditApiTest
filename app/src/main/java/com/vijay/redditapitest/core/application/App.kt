package com.vijay.redditapitest.core.application

import android.app.Activity
import android.app.Application
import com.vijay.redditapitest.core.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjection: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjection

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        AppInjector.init(this)
    }
}