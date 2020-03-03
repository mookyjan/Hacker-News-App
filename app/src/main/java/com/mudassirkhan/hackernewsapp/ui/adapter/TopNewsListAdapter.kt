package com.mudassirkhan.hackernewsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.databinding.SingleItemTopNewsBinding
import com.mudassirkhan.hackernewsapp.ui.model.NewsItem

class TopNewsListAdapter (private val topNewsList : List<NewsItem>, private val callbacks: Callbacks? = null): RecyclerView.Adapter<TopNewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Timber.d { "news list $topNewsList" }
        val inflater = LayoutInflater.from(parent.context)
        val binding: SingleItemTopNewsBinding = DataBindingUtil.inflate(inflater, R.layout.single_item_top_news, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return topNewsList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = topNewsList.get(position)
        holder.binding.txtId.text = (position+1).toString()
        holder.binding.executePendingBindings()
    }

    interface Callbacks {
        fun onNewsItemClick(view: View, item: NewsItem)
    }

    inner class ViewHolder (val binding : SingleItemTopNewsBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener { callbacks?.onNewsItemClick(it, topNewsList!!.get(adapterPosition)) }
        }
    }

}