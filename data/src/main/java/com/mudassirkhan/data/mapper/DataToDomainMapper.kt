package com.mudassirkhan.data.mapper

import com.mudassirkhan.data.local.model.NewsLocalModel
import com.mudassirkhan.data.remote.entities.CommentsData

import com.mudassirkhan.data.remote.entities.NewsItem
import com.mudassirkhan.domain.entity.CommentsDomain

object DataToDomainMapper {

    fun mapDataToDomainNewsItem(dataNewsItem: NewsItem) = com.mudassirkhan.domain.entity.NewsItem(
        by = dataNewsItem.by,
        id = dataNewsItem.id,
        descendants = dataNewsItem.descendants,
        kids = dataNewsItem.kids,
        score = dataNewsItem.score,
        time = dataNewsItem.time,
        title = dataNewsItem.title,
        type = dataNewsItem.type,
        url = dataNewsItem.url
    )

    fun mapDataToDomainNewsItem(items: List<NewsItem>) = items.map { mapDataToDomainNewsItem(it) }


//    fun toLocal(dataNewsItem: NewsItem) = NewsLocalModel(
//        by = dataNewsItem.by,
//        id = dataNewsItem.id!!.toInt(),
//        descendants = dataNewsItem.descendants,
////        kids = dataNewsItem.kids,
//        score = dataNewsItem.score,
//        time = dataNewsItem.time,
//        title = dataNewsItem.title,
//        type = dataNewsItem.type,
//        url = dataNewsItem.url
//    )
//
//    fun toLocal(items: List<NewsItem>) = items.map { toLocal(it) }

    fun mapDataToDomainComments(dataNewsItem: CommentsData) = CommentsDomain(

        id = dataNewsItem.id,
        createdAt = dataNewsItem.createdAt,
        createdAtI = dataNewsItem.createdAtI,
        type = dataNewsItem.type,
        author = dataNewsItem.author,
        title = dataNewsItem.title,
        text = dataNewsItem.text,
        points = dataNewsItem.points,
        url = dataNewsItem.url,
        parentId = dataNewsItem.parentId,
        storyId = dataNewsItem.storyId,
        children = mapDataToDomainComments(dataNewsItem.children)

    )


    fun mapDataToDomainComments(items: List<CommentsData>?) : List<CommentsDomain>?{

      return  items?.map { mapDataToDomainComments(it) }
    }

}