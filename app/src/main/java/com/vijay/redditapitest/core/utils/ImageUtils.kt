package com.vijay.redditapitest.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.vijay.redditapitest.R

/**
 * Util for working with images
 */
object ImageUtils {

    @JvmStatic
    fun loadImage(imageView: ImageView?, imageUri: String?) {
        if (imageView == null || imageUri == null || imageUri.isEmpty()) {
            return
        }

        val options = RequestOptions()
            .error(R.drawable.image_placeholder)
            .format(DecodeFormat.PREFER_ARGB_8888)
            .fitCenter()
            .priority(Priority.HIGH)

        Glide.with(imageView.context)
            .load(imageUri)
            .apply(options)
            .into(imageView)
    }
}
