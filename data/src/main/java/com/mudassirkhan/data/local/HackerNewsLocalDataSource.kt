package com.mudassirkhan.data.local

import com.mudassirkhan.data.local.dao.NewsItemDao
import com.mudassirkhan.data.local.model.NewsLocalModel
import io.reactivex.Single

class HackerNewsLocalDataSource (private val newsDao: NewsItemDao) {

    fun getTopNewsList(): Single<List<NewsLocalModel>> = newsDao.getAllTopNewsList()

    fun insertAll(news: List<NewsLocalModel>)= newsDao.insertAll(*news.toTypedArray())

}
