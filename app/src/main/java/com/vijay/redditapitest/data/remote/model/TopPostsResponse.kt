package com.vijay.redditapitest.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.concurrent.TimeUnit

data class TopPostsResponse(
    @SerializedName("kind") val kind: String = "", // Listing
    @SerializedName("data") val data: Data = Data()
) {

    data class Data(
        @SerializedName("modhash") val modhash: String = "",
        @SerializedName("dist") val dist: Int = 0, // 10
        @SerializedName("children") val children: List<Children> = listOf(),
        @SerializedName("after") val after: String = "", // t3_9qenc7
        @SerializedName("before") val before: String = "" // t3_9p2oqt
    ) {

        data class Children(
            @SerializedName("kind") val kind: String = "", // t3
            @SerializedName("data") val generalInfo: GeneralInfo = GeneralInfo()
        ) {
            data class GeneralInfo(
                @SerializedName("subreddit") val subreddit: String = "", // funny
                @SerializedName("author_fullname") val authorFullname: String = "", // t2_1djxr4pl
                @SerializedName("name") val name: String = "", // t3_9qenc7
                @SerializedName("title") val title: String = "", // Guy fights off thieves with a bong
                @SerializedName("likes") val likes: Any = Any(), // null
                @SerializedName("view_count") val viewCount: Any = Any(), // null
                @SerializedName("url") val url: String = "", // https://v.redd.it/2x5xg9og5rt11
                @SerializedName("num_comments") val numComments: Int = 0, // 7503
                @SerializedName("created") val created: Int = 0, // 1540249564
                @SerializedName("score") val score: Int = 0, // 137848
                @SerializedName("subreddit_id") val subredditId: String = "",// t5_2qh33
                @SerializedName("author") val author: String = ""// SSpSpoSpouSpout
            ) {

                companion object {
                    const val TEMPLATE_CREATED = "hours ago"
                    const val TEMPLATE_POSTED_BY = "Posted by"
                    const val TEMPLATE_SUBREDDIT = "Subreddit"
                    const val TEMPLATE_COMMENTS = "Comments: "
                    const val TEMPLATE_VOTES = "Rating: "
                }

                fun getTimePosted(): String {
                    val hours = TimeUnit.SECONDS.toHours((System.currentTimeMillis() / 1000) - this@GeneralInfo.created)
                    return "$hours ${GeneralInfo.TEMPLATE_CREATED}"
                }

                fun getPostedByText(): String = "${GeneralInfo.TEMPLATE_POSTED_BY} ${this@GeneralInfo.author}\n" +
                        "${GeneralInfo.TEMPLATE_SUBREDDIT} - ${this@GeneralInfo.subreddit}"

                fun getCommentsAmmountText(): String = "${GeneralInfo.TEMPLATE_COMMENTS} ${this@GeneralInfo.numComments}"

                fun getRatingText(): String = "${GeneralInfo.TEMPLATE_VOTES} ${this@GeneralInfo.score}"
            }
        }
    }
}