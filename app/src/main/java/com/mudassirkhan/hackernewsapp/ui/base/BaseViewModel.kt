package com.mudassirkhan.hackernewsapp.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

abstract class BaseViewModel : ViewModel(){

    private val disposables = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        disposables += disposable
    }

    override fun onCleared() {
        disposables.dispose()
    }

}