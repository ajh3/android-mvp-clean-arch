package com.aaronhalbert.androidmvpdemo.domain.interactor.browse

import com.aaronhalbert.androidmvpdemo.domain.executor.PostExecutionThread
import com.aaronhalbert.androidmvpdemo.domain.executor.ThreadExecutor
import com.aaronhalbert.androidmvpdemo.domain.interactor.SingleUseCase
import com.aaronhalbert.androidmvpdemo.domain.model.Coworker
import com.aaronhalbert.androidmvpdemo.domain.repository.CoworkerRepository
import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Coworker] instances from the [CoworkerRepository]
 */
open class GetCoworkers @Inject constructor(
    private val coworkerRepository: CoworkerRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Coworker>, GetCoworkers.Params>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: GetCoworkers.Params): Single<List<Coworker>> {
        return coworkerRepository.getCoworkers(params)
    }

    data class Params(
        val fileName: String,
        val sortOrder: BrowseActivity.SortOrder
    )
}
