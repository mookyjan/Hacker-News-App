package com.mudassirkhan.data.remote

import com.mudassirkhan.data.remote.api.HackerNewsApiService
import com.mudassirkhan.data.remote.entities.NewsItem
import com.mudassirkhan.data.remote.entities.CommentsData

import io.reactivex.Single

class RemoteDataSource (private val apiService: HackerNewsApiService) {


    fun getHackerTopNewsList(): Single<List<String>> = apiService.getNewsTopStoriesList();

    fun getHackerTopNews(itemId : String) : Single<NewsItem> = apiService.getNewsItem(itemId = itemId)

    fun getNewsDetail(itemId: String) : Single<CommentsData> = apiService.getNewsDetail(itemId)
}