package com.vijay.redditapitest.core.network

import com.vijay.redditapitest.data.prefs.CorePreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(private val prefs: CorePreferenceHelper) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            if (prefs.accessToken.isNotEmpty()) {
                addHeader(AUTH_HEADER_KEY, prefs.accessToken)
            }
        }
        return chain.proceed(request.build())
    }

    companion object {
        private const val AUTH_HEADER_KEY = "Authorization"
    }
}