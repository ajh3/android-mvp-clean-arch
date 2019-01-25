package com.aaronhalbert.androidmvpdemo.presentation.browse

import com.aaronhalbert.androidmvpdemo.domain.interactor.CompletableUseCase
import com.aaronhalbert.androidmvpdemo.domain.interactor.SingleUseCase
import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.GetCoworkers
import com.aaronhalbert.androidmvpdemo.domain.model.Coworker
import com.aaronhalbert.androidmvpdemo.presentation.mapper.CoworkerMapper
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class BrowseCoworkersPresenter @Inject constructor(
    val browseView: BrowseCoworkersContract.View,
    private val getCoworkersUseCase: SingleUseCase<List<Coworker>, GetCoworkers.Params>,
    private val clearCoworkersUseCase: CompletableUseCase<Void>,
    private val coworkerMapper: CoworkerMapper
) : BrowseCoworkersContract.Presenter {

    private var coworkers: List<Coworker> = arrayListOf()

    init {
        browseView.setPresenter(this)
    }

    override fun start() {
        retrieveCoworkers()
    }

    override fun stop() {
        getCoworkersUseCase.dispose()
        clearCoworkersUseCase.dispose()
    }

    override fun retrieveCoworkers() {
        getCoworkersUseCase.execute(
            object : DisposableSingleObserver<List<Coworker>>() {
                override fun onSuccess(t: List<Coworker>) {
                    handleGetCoworkersSuccess(t)
                }

                override fun onError(e: Throwable) {
                    browseView.hideCoworkers()
                    browseView.hideEmptyState()
                    browseView.showErrorState()
                }

            },
            GetCoworkers.Params(
                browseView.fileName,
                browseView.sortOrder
            )
        )
    }

    /* callback lets us easily reload data sets, since the cache must be cleared before doing so */
    override fun clearCoworkers(callback: () -> Unit) {
        clearCoworkersUseCase.execute(
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    callback.invoke()
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    override fun refresh() {
        browseView.clearSortOrder()

        clearCoworkers { retrieveCoworkers() }
    }

    private fun handleGetCoworkersSuccess(coworkers: List<Coworker>) {
        this.coworkers = coworkers

        browseView.hideErrorState()
        if (coworkers.isNotEmpty()) {
            browseView.hideEmptyState()
            browseView.showCoworkers(coworkers.map { coworkerMapper.mapToView(it) })
        } else {
            browseView.hideCoworkers()
            browseView.showEmptyState()
        }
    }
}
