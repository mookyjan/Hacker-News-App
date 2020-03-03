package com.mudassirkhan.hackernewsapp.ui.news_detail.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.databinding.ArticleFragmentBinding
import com.mudassirkhan.hackernewsapp.ui.MainActivity
import com.mudassirkhan.hackernewsapp.ui.news_detail.NewsDetailViewModel
import kotlinx.android.synthetic.main.article_fragment.*


class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    private lateinit var mBinding: ArticleFragmentBinding
    private val viewModel: NewsDetailViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       mBinding= DataBindingUtil.inflate(inflater,R.layout.article_fragment, container, false)
        observeEvents()
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webView.setWebViewClient(MyWebViewClient())
        openURL()
    }

    fun observeEvents(){
        viewModel.loading.observe(this, Observer {
            (activity as MainActivity).showProgress(it)
        })
    }

    /** Opens the URL in a browser  */
    private fun openURL() {

        viewModel.newsDetailItem.observe(this, Observer {
            Timber.d { "comment detail url  ${it.url}" }
            webView.loadUrl(it.url)
            webView.requestFocus()
        })

    }



    private class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

}
