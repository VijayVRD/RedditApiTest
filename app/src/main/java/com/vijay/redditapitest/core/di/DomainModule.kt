package com.vijay.redditapitest.core.di

import com.vijay.redditapitest.data.prefs.CorePreferenceHelper
import com.vijay.redditapitest.data.remote.PostsService
import com.vijay.redditapitest.data.remote.TokenApiService
import com.vijay.redditapitest.mainScope.model.PostsRepository
import com.vijay.redditapitest.mainScope.presentation.TopPostsPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class DomainModule {

    @Provides
    fun topPostsRepo(
        postService: PostsService,
        tokenApiService: TokenApiService,
        corePreferenceHelper: CorePreferenceHelper,
        compositeDisposable: CompositeDisposable
    ): PostsRepository =
        PostsRepository(
            postService,
            tokenApiService,
            corePreferenceHelper,
            compositeDisposable
        )

    @Singleton
    @Provides
    fun postService(retrofit: Retrofit): PostsService = retrofit.create(PostsService::class.java)

    @Singleton
    @Provides
    fun tokenService(retrofit: Retrofit): TokenApiService = retrofit.create(TokenApiService::class.java)

    @Provides
    fun topPostsPresenter(
        repository: PostsRepository,
        compositeDisposable: CompositeDisposable
    ): TopPostsPresenter =
        TopPostsPresenter(compositeDisposable, repository)
}