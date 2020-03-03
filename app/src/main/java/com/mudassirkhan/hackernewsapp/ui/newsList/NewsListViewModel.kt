package com.mudassirkhan.hackernewsapp.ui.newsList

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.domain.interactor.GetHackerNewsItemUseCase
import com.mudassirkhan.domain.interactor.GetHackerNewsListUseCase
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.mapper.mapDomainToPresentationNewsItem
import com.mudassirkhan.hackernewsapp.ui.base.BaseViewModel
import com.mudassirkhan.hackernewsapp.ui.model.NewsItem
import com.mudassirkhan.hackernewsapp.utils.IResourceProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val newsIdListUseCase: GetHackerNewsListUseCase,
    private val newsItemUseCase: GetHackerNewsItemUseCase,
    private val iResourceProvider: IResourceProvider
) : BaseViewModel() {

    //variable for the news list
    var newsList = ObservableArrayList<String>()
    var topNewsList = ObservableArrayList<NewsItem>()
    var presentationNewsItem = NewsItem()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    //variable for loading progress
    val loading = MutableLiveData<Boolean>()
    //variable for error message
    val error = MutableLiveData<String>()
    val empty = ObservableBoolean(true)


    /**
     * fun to get the list of news id from api
     */
    fun getNewsList() {
        loading.postValue(true)
        newsIdListUseCase.execute().subscribeBy(onSuccess = { t ->
            Timber.d { "news  api response success ${t.size}" }
            loading.postValue(false)
            newsList.clear()
            newsList.addAll(t)
            //Take only top 25 news Ids
            val list: List<String> = newsList.take(25);
            list.forEach {
                getNewsItem(it)
            }
            empty.set(t.isEmpty())

        }, onError = { e ->

            Timber.e { "newsList api error $e" }
            loading.postValue(false)
            error.postValue(e.localizedMessage ?: e.message ?: "Unknown error")

        }).addTo(compositeDisposable)


    }

    fun getNewsItem(itemId: String) {

        newsItemUseCase.setParam(itemId)
        newsItemUseCase.execute().subscribeBy(onSuccess = { t ->
            Timber.d { "get news detail response success ${t.title}" }
            loading.postValue(false)

            loading.postValue(false)
            presentationNewsItem = mapDomainToPresentationNewsItem(t)
            topNewsList.clear()
            topNewsList.add(presentationNewsItem)
        }, onError = { exception ->
            //TODO error handling can be improved
            Timber.e { "get news detail response error $exception" }
            loading.postValue(false)
            error.postValue(
                exception.localizedMessage ?: exception.message
                ?: iResourceProvider.context.getString(
                    R.string.txt_error
                )
            )
        }).addTo(compositeDisposable)

    }

}
