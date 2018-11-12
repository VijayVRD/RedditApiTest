package com.vijay.redditapitest.core.widget

import android.support.v7.widget.RecyclerView

const val VISIBLE_THRESHOLD = 5

abstract class EndlessRecyclerListener : RecyclerView.OnScrollListener() {
    var previousTotalItemCount = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var firstVisibleItem: Int = 0

    var isLoadingMore = true

    fun reset() {
        previousTotalItemCount = 0
    }
}