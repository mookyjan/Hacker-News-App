package com.mudassirkhan.data.remote.entities

import com.squareup.moshi.Json

data class NewsItem (
    @Json(name = "by")
     val by: String? = null,
    @Json(name = "descendants")
     val descendants: Int? = null,
    @Json(name = "id")
     val id: Long? = null,
    @Json(name = "kids")
     val kids: List<Long>? = null,
    @Json(name = "score")
     val score: Int? = null,
    @Json(name = "time")
     val time: Int? = null,
    @Json(name = "title")
     val title: String? = null,
    @Json(name = "type")
     val type: String? = null,
    @Json(name = "url")
     val url: String? = null
)