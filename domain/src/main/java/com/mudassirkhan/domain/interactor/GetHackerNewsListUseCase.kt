package com.mudassirkhan.domain.interactor

import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.UseCase
import com.mudassirkhan.domain.entity.NewsItem
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import io.reactivex.Single

class GetHackerNewsListUseCase (schedulers: Schedulers,
                                private val hackerNewsGateway: HackerNewsGateway) : UseCase<List<String>>(schedulers) {

    override fun buildUseCaseObservable(): Single<List<String>> {
        val result = hackerNewsGateway.getHackerNewsList()
        return result
    }


}