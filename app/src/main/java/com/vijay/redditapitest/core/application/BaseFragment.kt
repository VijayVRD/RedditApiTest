package com.vijay.redditapitest.core.application

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vijay.redditapitest.core.di.Injectable
import com.vijay.redditapitest.core.mvp.presenter.BasePresenter
import com.vijay.redditapitest.core.mvp.view.BaseView
import com.vijay.redditapitest.core.utils.AppExecutors
import com.vijay.redditapitest.mainScope.presentation.MainActivity
import javax.inject.Inject

abstract class BaseFragment<T : BaseView> : Fragment(), BaseView, Injectable {

    @Inject
    lateinit var appExecutors: AppExecutors

    @get:LayoutRes
    protected abstract val layoutResource: Int

    private var presenter: BasePresenter<*>? = null

    override fun setPresenter(paramPresenter: BasePresenter<*>) {
        this.presenter = paramPresenter
    }

    protected abstract fun inject(fragment: BaseFragment<T>)

    protected abstract fun initViews()

    @CallSuper
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onError(throwable: Throwable?) {
        (activity as MainActivity?)?.onError(throwable)
    }
}