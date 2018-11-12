package com.vijay.redditapitest.core.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.vijay.redditapitest.core.application.App
import com.vijay.redditapitest.core.application.BaseFragment
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Singleton

/**
 * Helper class to automatically inject fragments if they extends [BaseFragment].
 */
@Singleton
object AppInjector {

    object FragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            if (f is Injectable) {
                AndroidSupportInjection.inject(f)
            }
        }
    }

    fun init(app: App) {
        DaggerAppComponent.builder().bindApplication(app).build().inject(app)

        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {
                //do nothing
            }

            override fun onActivityResumed(activity: Activity) {
                //do nothing
            }

            override fun onActivityPaused(activity: Activity) {
                //do nothing
            }

            override fun onActivityStopped(activity: Activity) {
                //do nothing
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
                //do nothing
            }

            override fun onActivityDestroyed(activity: Activity) {
                //do nothing
            }
        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks, true)
        }
    }
}