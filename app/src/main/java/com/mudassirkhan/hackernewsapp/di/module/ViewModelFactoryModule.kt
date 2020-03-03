package com.mudassirkhan.hackernewsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mudassirkhan.hackernewsapp.ui.newsList.NewsListViewModel
import com.mudassirkhan.hackernewsapp.ui.news_detail.NewsDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun provideNewsListViewModel(viewModel: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel::class)
    internal abstract fun provideNewsDetailViewModel(viewModel: NewsDetailViewModel): ViewModel



}