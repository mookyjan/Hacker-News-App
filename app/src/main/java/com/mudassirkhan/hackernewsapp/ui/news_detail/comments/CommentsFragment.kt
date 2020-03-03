package com.mudassirkhan.hackernewsapp.ui.news_detail.comments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber

import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.databinding.CommentsFragmentBinding
import com.mudassirkhan.hackernewsapp.ui.MainActivity
import com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation
import com.mudassirkhan.hackernewsapp.ui.news_detail.NewsDetailFragment
import com.mudassirkhan.hackernewsapp.ui.news_detail.NewsDetailViewModel
import com.mudassirkhan.hackernewsapp.ui.news_detail.comments.adapter.ExpandableCommentGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.comments_fragment.*
import javax.inject.Inject

class CommentsFragment : Fragment() {

    companion object {
        fun newInstance() = CommentsFragment()
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private lateinit var viewModel: NewsDetailViewModel
    private lateinit var mBinding : CommentsFragmentBinding
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private lateinit var groupLayoutManager: GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.comments_fragment, container, false)

        groupLayoutManager = GridLayoutManager(activity, groupAdapter.spanCount).apply {
            spanSizeLookup = groupAdapter.spanSizeLookup
        }
        mBinding.rvComments.apply {
            layoutManager = groupLayoutManager
            adapter = groupAdapter
        }
//        observerEvents()

        return mBinding.root
    }


    fun  observerEvents(){
        viewModel.newsDetailItem.observe(this, Observer {
            Timber.d { "comment newsDetail  ${it}" }

            //TODO to see from the first comment directly pass it to the adpater otherwise for child comments
            it?.children?.let {
                it.forEach {
                    groupAdapter.add(ExpandableCommentGroup(it))
                }
            }
        })


        viewModel.loading.observe(this, Observer {
            (activity as MainActivity).showProgress(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        activity?.let {
        viewModel = ViewModelProviders.of(it, this.viewModelFactory)
            .get(NewsDetailViewModel::class.java)
        }
        observerEvents()
    }

}
