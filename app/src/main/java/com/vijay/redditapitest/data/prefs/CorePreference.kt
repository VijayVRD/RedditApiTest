package com.vijay.redditapitest.data.prefs

interface CorePreference {

    var accessToken: String

    fun getUUID(): String
}