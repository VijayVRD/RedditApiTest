package com.vijay.redditapitest.core.extensions

import com.vijay.redditapitest.core.network.Outcome
import io.reactivex.subjects.PublishSubject

/**
 * Extension function to push a failed event with an exception to the observing outcome
 * */
fun <T> PublishSubject<Outcome<T>>.failed(e: Throwable?) {
    with(this) {
        loading(false)
        onNext(Outcome.failure(e))
    }
}

/**
 * Extension function to push  a success event with data to the observing outcome
 * */
fun <T> PublishSubject<Outcome<T>>.success(t: T) {
    with(this) {
        loading(false)
        onNext(Outcome.success(t))
    }
}

/**
 * Extension function to push the loading status to the observing outcome
 * */
fun <T> PublishSubject<Outcome<T>>.loading(isLoading: Boolean) {
    this.onNext(Outcome.loading(isLoading))
}