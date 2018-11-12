package com.vijay.redditapitest.mainScope.presentation

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vijay.redditapitest.R
import com.vijay.redditapitest.core.utils.AppExecutors
import com.vijay.redditapitest.core.utils.ImageUtils
import com.vijay.redditapitest.core.widget.DataBoundListAdapter
import com.vijay.redditapitest.data.remote.model.TopPostsResponse
import com.vijay.redditapitest.data.remote.model.TopPostsResponse.Data.Children.GeneralInfo
import com.vijay.redditapitest.databinding.ItemRvTopPostBinding

class TopPostsRecyclerAdapter constructor(
    appExecutors: AppExecutors,
    private val callback: ((GeneralInfo) -> Unit)
) : DataBoundListAdapter<TopPostsResponse.Data.Children.GeneralInfo, ItemRvTopPostBinding>(

    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<GeneralInfo>() {
        override fun areItemsTheSame(oldItem: GeneralInfo, newItem: GeneralInfo): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: GeneralInfo, newItem: GeneralInfo): Boolean {
            return oldItem.title == newItem.title
        }
    }
) {
    override fun bind(binding: ItemRvTopPostBinding, item: GeneralInfo) {
        binding.postData = item
        ImageUtils.loadImage(binding.imgvThumbnail, item.url)
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemRvTopPostBinding {
        val binding = DataBindingUtil.inflate<ItemRvTopPostBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_rv_top_post,
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.postData?.let { data ->
                callback.invoke(data)
            }
        }
        return binding
    }
}