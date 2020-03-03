package com.mudassirkhan.data.remote.repositories

import com.mudassirkhan.data.local.HackerNewsLocalDataSource
import com.mudassirkhan.data.remote.RemoteDataSource
import com.mudassirkhan.data.remote.entities.NewsItem
import com.mudassirkhan.data.remote.entities.CommentsData
import io.reactivex.Single

class HackerTopNewsListRepository(private val remoteDataSource: RemoteDataSource,
                                  private val newsLocalDataSource : HackerNewsLocalDataSource) {


    fun getTopNewsList(): Single<List<String>>{

        return remoteDataSource.getHackerTopNewsList();
    }

    fun getTopNews(itemId: String): Single<NewsItem>{
//        val local = newsLocalDataSource.getTopNewsList()
//            .filter{!it.isEmpty()}
//        val remote = remoteDataSource.getHackerTopNews(itemId)
        //TODO here we can also store the list of news locally
//        val remote = remoteDataSource.getHackerTopNews(itemId)
//            .toObservable()
//            .map { mapper.toLocal(it.newsList!!) }
//            .doOnNext {
//                print("newslist local $it")
//                newsLocalDataSource.insertAll(it)
//            }
//
//        return Observable.concat(local.toObservable(),remote)
//            .firstElement()
//            .toSingle()
        return  remoteDataSource.getHackerTopNews(itemId);
    }

    fun getNewsDetail(itemId: String) : Single<CommentsData>{

        val remote = remoteDataSource.getNewsDetail(itemId)
        return remote;
    }

}