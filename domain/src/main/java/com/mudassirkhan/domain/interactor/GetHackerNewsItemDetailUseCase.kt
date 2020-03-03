package com.mudassirkhan.domain.interactor

import com.mudassirkhan.domain.MissingUseCaseParameterException
import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.UseCase
import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.domain.entity.NewsItem
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import io.reactivex.Single

class GetHackerNewsItemDetailUseCase (schedulers: Schedulers,
                                      private val hackerNewsGateway: HackerNewsGateway
) : UseCase<CommentsDomain>(schedulers) {

     var itemId: String ?=null

    public fun setParam(itemId: String) {
        this.itemId = itemId
    }

    override fun buildUseCaseObservable(): Single<CommentsDomain> {
        if (itemId == null) throw MissingUseCaseParameterException(javaClass)
       else{
            val result = hackerNewsGateway.getHackerNewsDetails(itemId!!)
            return result
        }

    }
}