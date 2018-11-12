package com.vijay.redditapitest.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @Expose
    @SerializedName(ACCESS_TOKEN_KEY) val accessToken: String?,

    @Expose
    @SerializedName(TOKEN_TYPE_KEY) val type: String?,

    @Expose
    @SerializedName(EXPIRES_IN_KEY) val expires: Long? = 0L

) {
    companion object {
        //Response
        const val ACCESS_TOKEN_KEY = "access_token"
        const val TOKEN_TYPE_KEY = "token_type"
        const val EXPIRES_IN_KEY = "expires_in"
    }
}