package com.mudassirkhan.domain.entity

data class NewsItem (

     val by: String? = null,

     val descendants: Int? = null,

     val id: Long? = null,

     val kids: List<Long>? = null,

     val score: Int? = null,

     val time: Int? = null,

     val title: String? = null,

     val type: String? = null,

     val url: String? = null
)