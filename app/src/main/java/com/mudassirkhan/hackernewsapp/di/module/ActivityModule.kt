package com.mudassirkhan.hackernewsapp.di.module

import com.mudassirkhan.hackernewsapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeLoginActivity():MainActivity
}