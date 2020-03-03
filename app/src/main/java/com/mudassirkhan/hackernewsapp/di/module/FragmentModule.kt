package com.mudassirkhan.hackernewsapp.di.module

import com.mudassirkhan.hackernewsapp.ui.newsList.NewsListFragment
import com.mudassirkhan.hackernewsapp.ui.news_detail.NewsDetailFragment
import com.mudassirkhan.hackernewsapp.ui.news_detail.comments.CommentsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun newsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    internal abstract fun newsDetailFragment(): NewsDetailFragment

    @ContributesAndroidInjector
    internal abstract fun commentsFragment(): CommentsFragment




}