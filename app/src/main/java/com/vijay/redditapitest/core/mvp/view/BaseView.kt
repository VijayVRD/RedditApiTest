package com.vijay.redditapitest.core.mvp.view

import com.vijay.redditapitest.core.mvp.presenter.BasePresenter

/**
 * Each view should extend from this interface
 */
interface BaseView {
    fun onError(throwable: Throwable?)
    fun setPresenter(paramPresenter: BasePresenter<*>)
}