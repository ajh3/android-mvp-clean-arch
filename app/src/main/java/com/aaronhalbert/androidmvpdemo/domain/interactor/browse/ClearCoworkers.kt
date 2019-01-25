package com.aaronhalbert.androidmvpdemo.domain.interactor.browse

import com.aaronhalbert.androidmvpdemo.domain.executor.PostExecutionThread
import com.aaronhalbert.androidmvpdemo.domain.executor.ThreadExecutor
import com.aaronhalbert.androidmvpdemo.domain.interactor.CompletableUseCase
import com.aaronhalbert.androidmvpdemo.domain.model.Coworker
import com.aaronhalbert.androidmvpdemo.domain.repository.CoworkerRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Use case for clearing all [Coworker] instances from the [CoworkerRepository]
 */
open class ClearCoworkers @Inject constructor(
    private val coworkerRepository: CoworkerRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseCompletable(params: Void?): Completable {
        return coworkerRepository.clearCoworkers()
    }
}
