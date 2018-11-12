package com.vijay.redditapitest.data.remote

import com.vijay.redditapitest.data.remote.model.TopPostsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {

    @GET("/top/.json")
    fun getTopPosts(
        @Query("t") time: String = PERIOD,
        @Query("count") count: Int = COUNT,
        @Query("include_categories") includeCategories: Boolean = INCLUDE_CATEGORIES,
        @Query("limit") limit: Int = LIMIT,
        @Query("show") show: String = SHOW,
        @Query("sr_detail") sr_detail: String = DETAILS
    ): Single<TopPostsResponse>

    @GET("/top/.json")
    fun getTopPostsAfter(
        @Query("after") after: String,
        @Query("t") time: String = PERIOD,
        @Query("count") count: Int = COUNT,
        @Query("include_categories") includeCategories: Boolean = INCLUDE_CATEGORIES,
        @Query("limit") limit: Int = LIMIT,
        @Query("show") show: String = SHOW,
        @Query("sr_detail") sr_detail: String = DETAILS
    ): Single<TopPostsResponse>

    companion object {
        //Default values
        private const val PERIOD = "month"
        private const val COUNT = 15
        private const val INCLUDE_CATEGORIES = true
        private const val LIMIT = 25
        private const val SHOW = "all"
        private const val DETAILS = "yes"


    }
}

