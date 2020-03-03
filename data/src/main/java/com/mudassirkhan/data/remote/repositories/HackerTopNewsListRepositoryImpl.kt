package com.mudassirkhan.data.remote.repositories

import com.mudassirkhan.data.mapper.DataToDomainMapper.mapDataToDomainComments
import com.mudassirkhan.data.mapper.DataToDomainMapper.mapDataToDomainNewsItem
import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.domain.entity.NewsItem
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class HackerTopNewsListRepositoryImpl(private val hackerTopNewsListRepository: HackerTopNewsListRepository) :
    HackerNewsGateway {


    override fun getHackerNewsList(): Single<List<String>> {

        return Single.create { emitter ->

            hackerTopNewsListRepository.getTopNewsList()
                .subscribeBy(
                    onSuccess = { emitter.onSuccess(it) },
                    onError = { emitter.onError(it) }
                )
        }
    }


    override fun getHackerNewsItem(itemId: String): Single<NewsItem> {

        return Single.create { emitter ->
            hackerTopNewsListRepository.getTopNews(itemId)
                .subscribeBy(
                    onSuccess = { emitter.onSuccess(mapDataToDomainNewsItem(it)) },
                    onError = { emitter.onError(it) }
                )

        }

    }

    override fun getHackerNewsDetails(itemId: String): Single<CommentsDomain> {

        return Single.create { emitter ->
            hackerTopNewsListRepository.getNewsDetail(itemId)
                .subscribeBy(
                    onSuccess = { emitter.onSuccess(mapDataToDomainComments(it)) },
                    onError = { emitter.onError(it) }

                )
        }

    }


}