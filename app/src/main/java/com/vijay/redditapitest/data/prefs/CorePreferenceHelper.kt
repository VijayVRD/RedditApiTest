package com.vijay.redditapitest.data.prefs

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class CorePreferenceHelper(context: Context) : CorePreference {

    private var preferences: SharedPreferences? = null

    init {
        preferences = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override var accessToken: String
        get() = preferences?.getString(PREFS_TOKEN_KEY, "") ?: ""
        set(value) {
            preferences?.edit()?.putString(PREFS_TOKEN_KEY, "$BEARER_KEY$value")?.apply()
        }

    override fun getUUID(): String {
        var uuid = preferences?.getString(DEVICE_ID_KEY, "")
        return if (uuid.isNullOrEmpty()) {
            uuid = UUID.randomUUID().toString()
            preferences?.edit()?.putString(DEVICE_ID_KEY, uuid)?.apply()
            uuid
        } else uuid
    }

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val PREFS_TOKEN_KEY = "token"
        private const val DEVICE_ID_KEY = "device.uuid"

        private const val BEARER_KEY = "Bearer"
    }
}