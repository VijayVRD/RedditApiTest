package com.vijay.redditapitest.mainScope.presentation

import android.annotation.SuppressLint
import com.vijay.redditapitest.core.mvp.presenter.BasePresenter
import com.vijay.redditapitest.core.mvp.view.BaseView
import com.vijay.redditapitest.core.network.Outcome
import com.vijay.redditapitest.data.remote.model.TopPostsResponse
import com.vijay.redditapitest.mainScope.model.PostsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class TopPostsPresenter @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val repository: PostsRepository
) : BasePresenter<TopPostsPresenter.View>(compositeDisposable) {
    private val postsOutcome: PublishSubject<Outcome<TopPostsResponse>> by lazy {
        repository.postOutcomeTopPosts
    }

    @SuppressLint("CheckResult")
    fun getTopPosts() {
        postsOutcome.subscribe {
            when (it) {
                is Outcome.Success -> {
                    val postsList = ArrayList<TopPostsResponse.Data.Children.GeneralInfo>()
                    it.data.data.children.forEach { child ->
                        postsList.add(child.generalInfo)
                    }
                    view?.onPostsLoaded(postsList)
                }
                is Outcome.Progress -> {
                    view?.onLoading(it.loading)
                }
                is Outcome.Failure -> {
                    view?.onError(it.e)
                }
            }
        }
        repository.getTopPosts().subscribe()
    }

    @SuppressLint("CheckResult")
    fun onLoadMore() {
        view?.showMoreProgress()
        postsOutcome.subscribe {
            when (it) {
                is Outcome.Success -> {
                    val postsList = ArrayList<TopPostsResponse.Data.Children.GeneralInfo>()
                    it.data.data.children.forEach { child ->
                        postsList.add(child.generalInfo)
                    }
                    view?.apply {
                        hideProgress()
                        onPostsLoaded(postsList)
                    }
                }
                is Outcome.Progress -> {
                    view?.showMoreProgress()
                }
                is Outcome.Failure -> {
                    view?.apply {
                        onError(it.e)
                        hideProgress()
                    }
                }
            }
        }
        repository.getPostsAfter().subscribe()
    }

    interface View : BaseView {
        fun onLoading(isLoading: Boolean)
        fun onPostsLoaded(data: List<TopPostsResponse.Data.Children.GeneralInfo>)
        fun showMoreProgress()
        fun hideProgress()
    }
}