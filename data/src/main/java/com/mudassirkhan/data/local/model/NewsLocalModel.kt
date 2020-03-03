package com.mudassirkhan.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsItem")
data class NewsLocalModel (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "by")
     val by: String? = null,
    @ColumnInfo(name = "descendants")
     val descendants: Int? = null,
//    @ColumnInfo(name = "kids")
//    val kids: List<Long>? = null,
    @ColumnInfo(name = "score")
    val score: Int? = null,
    @ColumnInfo(name = "time")
    val time: Int? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "type")
    val type: String? = null,
    @ColumnInfo(name = "url")
    val url: String? = null
)