package com.aaronhalbert.androidmvpdemo.domain.interactor

import com.aaronhalbert.androidmvpdemo.domain.executor.PostExecutionThread
import com.aaronhalbert.androidmvpdemo.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class SingleUseCase<T, in Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params): Single<T>

    open fun execute(singleObserver: DisposableSingleObserver<T>, params: Params) {
        val single = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler) as Single<T>
        addDisposable(single.subscribeWith(singleObserver))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
