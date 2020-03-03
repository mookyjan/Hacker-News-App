package com.mudassirkhan.hackernewsapp.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mudassirkhan.hackernewsapp.ui.model.NewsItem

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("newsListAdapter", "newsListCallbacks", requireAll = false)
    fun setProductAdapter(recyclerView: RecyclerView, items: List<NewsItem>?, callbacks: TopNewsListAdapter.Callbacks?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = TopNewsListAdapter(it,callbacks)
        }
    }
}