package com.vijay.redditapitest.core.widget

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessLinearRecyclerListener(private val lm: LinearLayoutManager) : EndlessRecyclerListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = lm.itemCount
        firstVisibleItem = lm.findFirstVisibleItemPosition()

        if (isLoadingMore) {
            if (totalItemCount > previousTotalItemCount) {
               updateStatus()
            }
        }

        if (!isLoadingMore && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            onLoadMore()
            isLoadingMore = true
        }
    }

    abstract fun onLoadMore()

    fun updateStatus(){
        isLoadingMore = false
        previousTotalItemCount = totalItemCount
    }
}