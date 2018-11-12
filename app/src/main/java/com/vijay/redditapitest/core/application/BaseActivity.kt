package com.vijay.redditapitest.core.application

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.vijay.redditapitest.R
import com.vijay.redditapitest.core.extensions.toast
import com.vijay.redditapitest.core.mvp.presenter.BasePresenter
import com.vijay.redditapitest.core.mvp.view.BaseView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BaseView, HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    private var presenter: BasePresenter<*>? = null
    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @get:LayoutRes
    abstract val layoutResource: Int

    override fun supportFragmentInjector() = supportFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        init(savedInstanceState)
    }

    /**
     * Initializations
     */
    abstract fun init(state: Bundle?)

    override fun onError(throwable: Throwable?) {
        toast(throwable?.message ?: getString(R.string.strange_error))
    }

    override fun setPresenter(paramPresenter: BasePresenter<*>) {
        presenter = paramPresenter
    }

    override fun onDestroy() {
        presenter?.detachView()
        presenter = null
        super.onDestroy()
    }
}