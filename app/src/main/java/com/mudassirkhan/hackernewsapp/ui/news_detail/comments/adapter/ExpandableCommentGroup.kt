package com.mudassirkhan.hackernewsapp.ui.news_detail.comments.adapter

import com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation

import com.mudassirkhan.hackernewsapp.ui.news_detail.comments.ExpandableCommentItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.NestedGroup

class ExpandableCommentGroup constructor(
    comment : CommentsPresentation,
    depth : Int = 0) : ExpandableGroup(ExpandableCommentItem(comment, depth)) {

    init {
        comment?.let {
            for (comm in comment.children!!) {
                add(ExpandableCommentGroup(comm, depth.plus(1)))
            }
        }



    }


}