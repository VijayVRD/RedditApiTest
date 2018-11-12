package com.vijay.redditapitest.mainScope.presentation

import android.os.Bundle
import com.vijay.redditapitest.R
import com.vijay.redditapitest.core.application.BaseActivity
import com.vijay.redditapitest.core.extensions.replaceFragment

class MainActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun init(state: Bundle?) {
        replaceFragment(TopEntriesFragment(), R.id.root, false)
    }
}
