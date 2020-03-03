package com.mudassirkhan.hackernewsapp.di.module

import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import com.mudassirkhan.domain.interactor.GetHackerNewsItemDetailUseCase
import com.mudassirkhan.domain.interactor.GetHackerNewsItemUseCase
import com.mudassirkhan.domain.interactor.GetHackerNewsListUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideNewsIdListUseCase(schedulers: Schedulers, hackerNewsGateway: HackerNewsGateway): GetHackerNewsListUseCase{
        return  GetHackerNewsListUseCase(schedulers,hackerNewsGateway)
    }

    @Provides
    fun provideNewsItemUseCase(schedulers: Schedulers,hackerNewsGateway: HackerNewsGateway): GetHackerNewsItemUseCase {
        return GetHackerNewsItemUseCase(schedulers, hackerNewsGateway)
    }


    @Provides
    fun provideNewsItemDetailUseCase(schedulers: Schedulers, hackerNewsGateway: HackerNewsGateway) : GetHackerNewsItemDetailUseCase{

        return GetHackerNewsItemDetailUseCase(schedulers,hackerNewsGateway)
    }
}