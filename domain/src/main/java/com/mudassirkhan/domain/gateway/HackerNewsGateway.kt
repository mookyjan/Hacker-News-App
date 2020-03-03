package com.mudassirkhan.domain.gateway

import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.domain.entity.NewsItem
import io.reactivex.Single

interface HackerNewsGateway {

    fun getHackerNewsList(): Single<List<String>>

    fun getHackerNewsItem(itemId: String) : Single<NewsItem>

    fun getHackerNewsDetails(itemId: String) : Single<CommentsDomain>

}