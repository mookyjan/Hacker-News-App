package com.mudassirkhan.hackernewsapp.ui.news_detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.domain.interactor.GetHackerNewsItemDetailUseCase
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.mapper.mapDomainToPresentationNewsItemDetail
import com.mudassirkhan.hackernewsapp.ui.model.CommentsPresentation
import com.mudassirkhan.hackernewsapp.utils.IResourceProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

open class NewsDetailViewModel @Inject constructor(
    private val hackerNewsItemDetailUseCase: GetHackerNewsItemDetailUseCase,
    private val iResourceProvider: IResourceProvider
) : ViewModel() {


    val newsDetailItem = MutableLiveData<CommentsPresentation>();
    val commentsList = MutableLiveData<List<CommentsPresentation>>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    //variable for loading progress
    val loading = MutableLiveData<Boolean>()
    //variable for error message
    val error = ObservableField<String>()
    val empty = ObservableBoolean(true)


    /**
     * get news detail
     */
    fun getNewsDetail(newsItemId: String) {
        loading.postValue(true)
        //setting the param to pass
        val url = "https://hn.algolia.com/api/v1/items/$newsItemId"
        hackerNewsItemDetailUseCase.setParam(url)
        hackerNewsItemDetailUseCase.execute().subscribeBy(onSuccess = { t ->
            Timber.d { "get news detail response success ${t.title}" }
            loading.postValue(false)

            t?.let {
                newsDetailItem.postValue(mapDomainToPresentationNewsItemDetail(it))

            }
        }, onError = { exception ->
            //TODO error handling can be improved
            Timber.e { "get news detail response error $exception" }
            loading.postValue(false)
            error.set(
                exception.localizedMessage ?: exception.message
                ?: iResourceProvider.context.getString(
                    R.string.txt_error
                )
            )
        }).addTo(compositeDisposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
//        newsDetailItem.value=null
    }


}
