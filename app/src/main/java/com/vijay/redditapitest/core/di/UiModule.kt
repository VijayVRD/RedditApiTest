package com.vijay.redditapitest.core.di

import com.vijay.redditapitest.mainScope.presentation.MainActivity
import com.vijay.redditapitest.mainScope.presentation.TopEntriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector(
        modules = [
            MainFragmentsBuilderModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}

@Module
abstract class MainFragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeTopEntriesFragment(): TopEntriesFragment
}