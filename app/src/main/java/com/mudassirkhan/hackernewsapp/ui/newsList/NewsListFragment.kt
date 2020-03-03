package com.mudassirkhan.hackernewsapp.ui.newsList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ajalt.timberkt.Timber

import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.core.Router
import com.mudassirkhan.hackernewsapp.databinding.NewsListFragmentBinding
import com.mudassirkhan.hackernewsapp.ui.MainActivity
import com.mudassirkhan.hackernewsapp.ui.adapter.TopNewsListAdapter
import com.mudassirkhan.hackernewsapp.ui.model.NewsItem
import com.mudassirkhan.hackernewsapp.utils.showView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.news_detail_fragment.*
import javax.inject.Inject

class NewsListFragment : Fragment(), TopNewsListAdapter.Callbacks  {

    companion object {
        fun newInstance() = NewsListFragment()
    }

    private lateinit var viewModel: NewsListViewModel

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private lateinit var mBinding : NewsListFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel=   ViewModelProviders.of(this, this.viewModelFactory).get(NewsListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.news_list_fragment, container, false)
        mBinding.viewModel = viewModel
        mBinding.onNewsItemCallback= this

        callApi()
        observeEvents()
        setupSwipeToRefresh()
        return mBinding.root

    }

    fun observeEvents(){
        viewModel.loading.observe(this, Observer {
//            (activity as MainActivity).showProgress
            if (it){
                showShimmer()
            }else{
                hideShimmer()
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewsListViewModel::class.java)

    }
    fun callApi(){
        viewModel.getNewsList()
    }

    /**
     * here the refresh listener only for concept no need because data already refresh after 1 sec
     */
    private fun setupSwipeToRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener(refreshListener)

    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        callApi()
        mBinding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showShimmer() {
        mBinding.shimmerViewContainer.showView(true)
        mBinding.shimmerViewContainer.startShimmerAnimation()
        mBinding.swipeRefreshLayout.showView(false)
    }

    private fun hideShimmer() {
        mBinding.shimmerViewContainer.stopShimmerAnimation()
        mBinding.swipeRefreshLayout.showView(true)
        mBinding.shimmerViewContainer.showView(false)

    }


    override fun onNewsItemClick(view: View, item: NewsItem) {

        Timber.d { "clicked on ${item.title} id : ${item.id}" }
        Toast.makeText(activity,"Clicked on ${item.title}", Toast.LENGTH_SHORT).show()
        val args = Bundle()
        args.putString(Router.NEWS_ITEM_ID,item.id.toString())
        findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment, args)
    }
}
