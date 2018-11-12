package com.vijay.redditapitest.data.remote

import com.vijay.redditapitest.data.remote.model.TokenResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenApiService {

    companion object TokenApiKeys {
        const val DEVICE_ID_KEY = "device_id"
        const val GRANT_TYPE_KEY = "grant_type"
        const val AUTH_HEADER_KEY = "Authorization"
    }

    @FormUrlEncoded
    @POST("api/v1/access_token")
    fun getAuthToken(
        @Header(AUTH_HEADER_KEY) authentication: String,
        @Field(DEVICE_ID_KEY) deviceId: String,
        @Field(GRANT_TYPE_KEY) grant_type: String
    ): Single<TokenResponse>
}

