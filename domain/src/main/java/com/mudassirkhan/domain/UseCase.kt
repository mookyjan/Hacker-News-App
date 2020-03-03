package com.mudassirkhan.domain

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver


abstract class UseCase<T> protected constructor(private val schedulers:Schedulers) {

    protected abstract fun buildUseCaseObservable(): Single<T>

    fun execute() : Single<T> {
        val observable = this.buildUseCaseObservable()
            .subscribeOn(schedulers.subscribeOn)
            .observeOn(schedulers.observeOn)
        return observable

    }

}