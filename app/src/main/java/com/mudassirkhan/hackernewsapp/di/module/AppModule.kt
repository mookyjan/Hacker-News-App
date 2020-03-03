package com.mudassirkhan.hackernewsapp.di.module

import android.app.Application
import android.content.Context
import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.hackernewsapp.di.module.scheduler.AppSchedulers
import com.mudassirkhan.hackernewsapp.utils.IResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    @Named("application.context")
    internal fun providesContext(application: Application): Context {
        return   application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideSchedulers(): Schedulers = AppSchedulers()

    @Provides
    @Singleton
    internal fun provideResource(context: Context) = IResourceProvider(context)
}