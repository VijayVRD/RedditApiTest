package com.vijay.redditapitest.mainScope.model

import com.vijay.redditapitest.core.extensions.*
import com.vijay.redditapitest.core.network.Outcome
import com.vijay.redditapitest.data.prefs.CorePreferenceHelper
import com.vijay.redditapitest.data.remote.PostsService
import com.vijay.redditapitest.data.remote.TokenApiService
import com.vijay.redditapitest.data.remote.model.TokenResponse
import com.vijay.redditapitest.data.remote.model.TopPostsResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject constructor(
    private val postsService: PostsService,
    private val tokenApiService: TokenApiService,
    private val corePrefs: CorePreferenceHelper,
    private val compositeDisposable: CompositeDisposable
) : TopPostsDataContract.Repository {

    private var after: String = ""

    override val postFetchOutcome: PublishSubject<Outcome<TokenResponse>> =
        PublishSubject.create<Outcome<TokenResponse>>()

    override val postOutcomeTopPosts: PublishSubject<Outcome<TopPostsResponse>> =
        PublishSubject.create<Outcome<TopPostsResponse>>()

    override fun handleError(error: Throwable?) {
        postFetchOutcome.failed(error)
    }

    override fun getTopPosts(): Observable<TopPostsResponse> {
        postFetchOutcome.loading(true)
        if (corePrefs.accessToken.isEmpty()) {
            compositeDisposable.add(getToken().subscribe())
        }
        return Observable.create {
            postsService.getTopPosts().performOnBackOutOnMain().subscribe({ response ->
                after = response.data.after
                postOutcomeTopPosts.success(response)
            }, { error ->
                handleError(error)
            }).addTo(compositeDisposable)
        }
    }

    override fun getToken(): Observable<TokenResponse> = Observable.create {
        postFetchOutcome.loading(true)
        tokenApiService.getAuthToken(
            AUTH_HEADER, corePrefs.getUUID(),
            GRANT_TYPE
        ).performOnBackOutOnMain()
            .subscribe({ tokenResponse ->
                corePrefs.accessToken = tokenResponse.accessToken ?: ""
                postFetchOutcome.success(tokenResponse)
            }, { error ->
                error?.let {
                    handleError(error)
                }
            }).addTo(compositeDisposable)
    }

    override fun getPostsAfter(): Observable<TopPostsResponse> {
        postFetchOutcome.loading(true)
        if (corePrefs.accessToken.isEmpty()) {
            compositeDisposable.add(getToken().subscribe())
        }
        return Observable.create {
            postsService.getTopPostsAfter(after).performOnBackOutOnMain().subscribe({ response ->
                after = response.data.after
                postOutcomeTopPosts.success(response)
            }, { error ->
                handleError(error)
            }).addTo(compositeDisposable)
        }
    }

    companion object {
        private const val GRANT_TYPE = "https://oauth.reddit.com/grants/installed_client"
        private const val CLIENT_ID = "Q-gbTxm6BjnLYw"

        private val AUTH_HEADER = okhttp3.Credentials.basic(CLIENT_ID, "")
    }

}