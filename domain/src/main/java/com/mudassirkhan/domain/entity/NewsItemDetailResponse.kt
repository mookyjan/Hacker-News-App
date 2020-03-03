package com.mudassirkhan.domain.entity

import com.squareup.moshi.Json


data class CommentsDomain (
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "created_at")
    var createdAt: String? = null,
    @Json(name = "created_at_i")
    var createdAtI: Int? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "author")
    var author: String? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "url")
    var url: String? = null,
    @Json(name = "text")
    var text: Any? = null,
    @Json(name = "points")
    var points: Int? = null,
    @Json(name = "parent_id")
    var parentId: Any? = null,
    @Json(name = "story_id")
    var storyId: Any? = null,
    @Json(name = "children")
    var children: List<CommentsDomain>? = null,
    @Json(name = "options")
    var options: List<Any>? = null
)

