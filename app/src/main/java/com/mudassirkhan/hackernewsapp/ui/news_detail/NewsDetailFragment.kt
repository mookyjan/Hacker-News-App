package com.mudassirkhan.hackernewsapp.ui.news_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.core.Router
import com.mudassirkhan.hackernewsapp.databinding.NewsDetailFragmentBinding
import com.mudassirkhan.hackernewsapp.ui.MainActivity
import com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.news_detail_fragment.*
import javax.inject.Inject

open class NewsDetailFragment : Fragment() {

    companion object {
        fun newInstance() = NewsDetailFragment()
    }
    private val model: NewsDetailViewModel by activityViewModels()
     lateinit var viewModel: NewsDetailViewModel
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    private var activity: AppCompatActivity? = null
    private lateinit var mBinding : NewsDetailFragmentBinding
    val commentList:  ArrayList<CommentsPresentation>  = ArrayList<CommentsPresentation>()
    var newsItemId :String? =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null)
            newsItemId = arguments?.getString(Router.NEWS_ITEM_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.news_detail_fragment, container, false)

        activity = getActivity() as AppCompatActivity?
        AndroidSupportInjection.inject(this)
        activity?.let {
            viewModel=   ViewModelProviders.of(it, this.viewModelFactory).get(NewsDetailViewModel::class.java)
        }

        mBinding.viewModel = viewModel
        callApi()

        observerEvents()
        return mBinding.root

    }


    fun setupViewPager(){
        assert(activity != null)
        val sectionsPagerAdapter = SectionsPagerAdapter(activity!!.baseContext, activity!!.supportFragmentManager)
        val viewPager: ViewPager = mBinding.root.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = mBinding.root.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }


    fun callApi(){
        viewModel.getNewsDetail(newsItemId!!)
    }


    fun  observerEvents(){
        viewModel.newsDetailItem.observe(this, Observer {

            title.text =it.title
            txt_url.text = it.url
            txt_time.text = it.createdAt
            it.children?.let {
                commentList.addAll(it)
            }
            setupViewPager()
        })

        viewModel.loading.observe(this, Observer {
            (activity as MainActivity).showProgress(it)
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }



}
