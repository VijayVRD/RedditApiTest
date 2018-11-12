package com.vijay.redditapitest.core.mvp.presenter

import com.vijay.redditapitest.core.mvp.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BasePresenter<V : BaseView> constructor(private var disposable: CompositeDisposable) : Presenter<V> {

    private var weakReference: WeakReference<V>? = null
    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = view != null

    override fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
            view.setPresenter(this)
        }
    }

    override fun detachView() {
        weakReference?.clear()
        weakReference = null
        disposable.clear()
    }
}