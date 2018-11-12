package com.vijay.redditapitest.core.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(
    fragment: Fragment, id: Int, isBackStack: Boolean,
    allowStateLoss: Boolean = false
) {
    supportFragmentManager.beginTransaction().apply {
        replace(id, fragment, fragment::class.java.simpleName)
        if (isBackStack) {
            addToBackStack(fragment::class.java.simpleName)
        }
        when {
            !supportFragmentManager.isStateSaved -> commit()
            allowStateLoss -> commitAllowingStateLoss()
        }
        show(fragment)
    }
}