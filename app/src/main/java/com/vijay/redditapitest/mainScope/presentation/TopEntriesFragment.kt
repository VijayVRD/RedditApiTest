package com.vijay.redditapitest.mainScope.presentation

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vijay.redditapitest.R
import com.vijay.redditapitest.core.application.BaseFragment
import com.vijay.redditapitest.core.mvp.presenter.BasePresenter
import com.vijay.redditapitest.core.widget.EndlessLinearRecyclerListener
import com.vijay.redditapitest.data.remote.model.TopPostsResponse
import kotlinx.android.synthetic.main.fragment_top_entries.*
import javax.inject.Inject

class TopEntriesFragment : BaseFragment<TopPostsPresenter.View>(),
    TopPostsPresenter.View {

    @Inject
    lateinit var presenter: TopPostsPresenter

    override val layoutResource: Int
        get() = R.layout.fragment_top_entries

    private var endless: EndlessLinearRecyclerListener? = null
    private val onPostClicked: ((TopPostsResponse.Data.Children.GeneralInfo) -> Unit) = {
        CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(it.url))
    }

    override fun inject(fragment: BaseFragment<TopPostsPresenter.View>) {}

    override fun initViews() {
        presenter.attachView(this@TopEntriesFragment)
        initRecyclerView()
        initRefreshLayout()
        presenter.getTopPosts()
    }

    private fun initRecyclerView() {
        (recyclerView?.layoutManager as LinearLayoutManager?)?.let {
            endless = object : EndlessLinearRecyclerListener(it) {
                override fun onLoadMore() {
                    presenter.onLoadMore()
                }
            }.apply {
                recyclerView.addOnScrollListener(this)
            }
        }
        recyclerView.adapter = TopPostsRecyclerAdapter(appExecutors, onPostClicked)
    }

    private fun initRefreshLayout() {
        refreshLayout.setOnRefreshListener { presenter.getTopPosts() }
    }

    override fun setPresenter(paramPresenter: BasePresenter<*>) {
        this.presenter = paramPresenter as TopPostsPresenter
    }

    override fun onLoading(isLoading: Boolean) {
        refreshLayout.isRefreshing = isLoading
    }

    override fun showMoreProgress() {
        moreProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        refreshLayout.isRefreshing = false
        moreProgress.visibility = View.GONE
    }

    override fun onPostsLoaded(data: List<TopPostsResponse.Data.Children.GeneralInfo>) {
        (recyclerView.adapter as TopPostsRecyclerAdapter?)?.let {
            endless?.updateStatus()
            it.submitList(data)
        }
    }

    override fun onDetach() {
        presenter.detachView()
        recyclerView.clearOnScrollListeners()
        endless = null
        super.onDetach()
    }
}
