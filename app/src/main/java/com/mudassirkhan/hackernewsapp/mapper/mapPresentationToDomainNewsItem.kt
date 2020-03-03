package com.mudassirkhan.hackernewsapp.mapper

import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation
import com.mudassirkhan.domain.entity.CommentsDomain as DomainNewsDetail
import com.mudassirkhan.domain.entity.NewsItem as DomainNewsItem
import com.mudassirkhan.hackernewsapp.ui.model.NewsItem as PresentationNewsItem



fun mapPresentationToDomainNewsItem(presentationNewsItem: PresentationNewsItem) = com.mudassirkhan.domain.entity.NewsItem(
    by = presentationNewsItem.by,
    id = presentationNewsItem.id,
    descendants = presentationNewsItem.descendants,
    kids = presentationNewsItem.kids,
    score = presentationNewsItem.score,
    time = presentationNewsItem.time,
    title = presentationNewsItem.title,
    type = presentationNewsItem.type,
    url = presentationNewsItem.url
)

fun mapDataToDomainNewsItem(items: List<PresentationNewsItem>) = items.map { mapPresentationToDomainNewsItem(it) }


fun mapDomainToPresentationNewsItem(domainNewsItem: DomainNewsItem) = PresentationNewsItem(
    by = domainNewsItem.by,
    id = domainNewsItem.id,
    descendants = domainNewsItem.descendants,
    kids = domainNewsItem.kids,
    score = domainNewsItem.score,
    time = domainNewsItem.time,
    title = domainNewsItem.title,
    type = domainNewsItem.type,
    url = domainNewsItem.url
)

fun mapDomainToPresentationNewsItem(domainNewsList: List<DomainNewsItem>) =
    domainNewsList.map {
        mapDomainToPresentationNewsItem(it)
    }

fun mapDomainToPresentationNewsItemDetail(domainNewsItemDetail: DomainNewsDetail) = CommentsPresentation(

    id = domainNewsItemDetail.id,
    createdAt = domainNewsItemDetail.createdAt,
    createdAtI = domainNewsItemDetail.createdAtI,
    type = domainNewsItemDetail.type,
    author = domainNewsItemDetail.author,
    title = domainNewsItemDetail.title,
    text = domainNewsItemDetail.text,
    points = domainNewsItemDetail.points,
    url = domainNewsItemDetail.url,
    parentId = domainNewsItemDetail.parentId,
    storyId = domainNewsItemDetail.storyId,
    children = mapDomainToPresentationNewsItemDetail(domainNewsItemDetail.children)
)


fun mapDomainCommentToPresentationComment(domainComment: CommentsDomain)=CommentsPresentation(
    id = domainComment.id,
    createdAt = domainComment.createdAt,
    createdAtI = domainComment.createdAtI,
    type = domainComment.type,
    author = domainComment.author,
    title = domainComment.title,
    text = domainComment.text,
    points = domainComment.points,
    url = domainComment.url,
    parentId = domainComment.parentId,
    storyId = domainComment.storyId
//    children = domainComment.children
)

fun mapDomainCommentToPresentationComment(domainNewsList: List<CommentsDomain>?) =
    domainNewsList?.map {
        mapDomainCommentToPresentationComment(it)
    }

fun mapDomainToPresentationNewsItemDetail(items: List<com.mudassirkhan.domain.entity.CommentsDomain>?) : List<com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation>?{

    return  items?.map { mapDomainToPresentationNewsItemDetail(it) }
}