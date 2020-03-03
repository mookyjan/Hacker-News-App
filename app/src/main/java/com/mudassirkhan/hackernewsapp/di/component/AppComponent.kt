package com.mudassirkhan.hackernewsapp.di.component

import android.app.Application
import android.content.Context
import com.mudassirkhan.hackernewsapp.HackerNewsApp
import com.mudassirkhan.hackernewsapp.di.module.*
import com.mudassirkhan.hackernewsapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    AndroidInjectionModule::class,
    ViewModelFactoryModule::class,
    ActivityModule::class,
    FragmentModule::class,
    NetworkModule::class,
    DataModule::class,
    DomainModule::class
))
interface AppComponent {

    fun inject(application: HackerNewsApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}