package com.mudassirkhan.hackernewsapp

import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.hackernewsapp.di.component.AppComponent
import com.mudassirkhan.hackernewsapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class HackerNewsApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityDispatcher: DispatchingAndroidInjector<Any>
    lateinit var component : AppComponent

    override fun androidInjector(): AndroidInjector<Any> {
        return  activityDispatcher
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        component = DaggerAppComponent
            .builder()
            .application(this)
            .context(this)
            .build()
        component.inject(this)
    }
}