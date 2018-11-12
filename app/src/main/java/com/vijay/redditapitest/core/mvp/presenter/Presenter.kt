package com.vijay.redditapitest.core.mvp.presenter

import com.vijay.redditapitest.core.mvp.view.BaseView

interface Presenter<V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}