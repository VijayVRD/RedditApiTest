package com.vijay.redditapitest.core.di

import android.app.Application
import android.content.Context
import com.vijay.redditapitest.data.prefs.CorePreferenceHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesContext(application: Application): Context {
        return application
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

//    @Provides
//    @Singleton
//    fun providesSharedPref(): CorePreferenceHelper = CorePreferenceHelper(context)

    @Singleton
    @Provides
    fun providesCorePrefs(application: Application): CorePreferenceHelper =
        CorePreferenceHelper(application)
}