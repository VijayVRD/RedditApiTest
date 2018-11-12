package com.vijay.redditapitest.mainScope.model

import com.vijay.redditapitest.core.network.Outcome
import com.vijay.redditapitest.data.remote.model.TokenResponse
import com.vijay.redditapitest.data.remote.model.TopPostsResponse
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface TopPostsDataContract {
    interface Repository {
        val postFetchOutcome: PublishSubject<Outcome<TokenResponse>>
        val postOutcomeTopPosts: PublishSubject<Outcome<TopPostsResponse>>
        fun handleError(error: Throwable?)

        fun getTopPosts(): Observable<TopPostsResponse>

        fun getToken(): Observable<TokenResponse>
        fun getPostsAfter(): Observable<TopPostsResponse>
    }

}