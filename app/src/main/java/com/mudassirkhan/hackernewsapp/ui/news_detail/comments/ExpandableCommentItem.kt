package com.mudassirkhan.hackernewsapp.ui.news_detail.comments

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.databinding.ItemExpandableCommentBinding
import com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import kotlinx.android.synthetic.main.item_expandable_comment.view.*


open class ExpandableCommentItem constructor(private val comment : CommentsPresentation,
                                             private val depth : Int)
                                          : BindableItem<ItemExpandableCommentBinding>(), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewBinding: ItemExpandableCommentBinding, position: Int) {
        addingDepthViews(viewBinding)
        viewBinding.tvUser.setText(comment.author)
        viewBinding.body.setText(Html.fromHtml(comment.text.toString()))
        viewBinding.tvComments.setText("${comment.children?.size} Comments")
        // by clicking on the no of comments open the replies
        viewBinding.tvComments.setOnClickListener {

            expandableGroup.onToggleExpanded()
            if (expandableGroup.isExpanded){
                viewBinding.tvComments.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
            }else{
                viewBinding.tvComments.setCompoundDrawablesWithIntrinsicBounds(0
                    , 0, R.drawable.ic_expand_less, 0);
            }
        }
    }


    private fun addingDepthViews(viewBinding: ItemExpandableCommentBinding) {
        viewBinding.separatorContainer.removeAllViews()
        viewBinding.separatorContainer.visibility =
            if (depth > 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
        for (i in 1..depth) {
            val v : View = LayoutInflater.from(viewBinding.root.context)
                .inflate(R.layout.layout_separator_view, viewBinding.separatorContainer, false)
            viewBinding.separatorContainer.addView(v);
        }
        viewBinding.body.requestLayout()
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }

    override fun getLayout(): Int = R.layout.item_expandable_comment



}