package com.mudassirkhan.data.remote.api

import com.mudassirkhan.data.remote.entities.CommentsData
import com.mudassirkhan.data.remote.entities.NewsItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface HackerNewsApiService {

    @GET("/v0/topstories.json")
    fun getNewsTopStoriesList(): Single<List<String>>

    @GET("/v0/item/{itemId}.json")
    fun getNewsItem(@Path("itemId") itemId: String): Single<NewsItem>

    //TODO for simplicity I have set the url like this because its change the Base Url, this can be changes later
//    "https://hn.algolia.com/api/v1/items/{itemId}
    @GET
    fun getNewsDetail(@Url itemId: String) : Single<CommentsData>
}