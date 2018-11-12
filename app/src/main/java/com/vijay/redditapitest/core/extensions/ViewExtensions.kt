package com.vijay.redditapitest.core.extensions

import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast

/**
 * Used to show [Toast] directly from any [View]
 * @param message Toast message
 */
fun FragmentActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}