package com.mudassirkhan.hackernewsapp.di.module

import android.content.Context
import com.mudassirkhan.data.local.HackerNewsLocalDataSource
import com.mudassirkhan.data.local.dao.NewsItemDao
import com.mudassirkhan.data.local.database.HackerNewsDatabase
import com.mudassirkhan.data.remote.RemoteDataSource
import com.mudassirkhan.data.remote.api.HackerNewsApiService
import com.mudassirkhan.data.remote.repositories.HackerTopNewsListRepository
import com.mudassirkhan.data.remote.repositories.HackerTopNewsListRepositoryImpl
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {


    @Provides
    @Singleton
    internal fun provideRemoteDataSource(hackerNewsApiService: HackerNewsApiService): RemoteDataSource {
        return RemoteDataSource(hackerNewsApiService)
    }

    @Provides
    @Singleton
    internal fun provideLocalDataSource(newsItemDao: NewsItemDao):HackerNewsLocalDataSource{
        return HackerNewsLocalDataSource(newsItemDao)
    }

    @Provides
    @Singleton
    internal fun provideHackerRepository(remoteDataSource: RemoteDataSource,localHackerNewsLocalDataSource: HackerNewsLocalDataSource):HackerTopNewsListRepository{
        return HackerTopNewsListRepository(remoteDataSource,localHackerNewsLocalDataSource )
    }

    @Provides
    @Singleton
    internal fun provideHackerGateway(hackerNewsRepository: HackerTopNewsListRepository): HackerNewsGateway{
        return HackerTopNewsListRepositoryImpl(hackerNewsRepository)
    }




    @Provides
    @Singleton
    internal fun provideHackerNewsDatabase(context: Context): HackerNewsDatabase {

        return HackerNewsDatabase.newInstance(context)
    }

    @Provides
    @Singleton
    internal fun provideHackerNewsDao(hackernewDatabase: HackerNewsDatabase): NewsItemDao = hackernewDatabase.hackerNewsDao()
}