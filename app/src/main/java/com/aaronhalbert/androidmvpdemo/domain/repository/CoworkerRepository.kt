package com.aaronhalbert.androidmvpdemo.domain.repository

import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.GetCoworkers
import com.aaronhalbert.androidmvpdemo.domain.model.Coworker
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface CoworkerRepository {
    fun clearCoworkers(): Completable
    fun saveCoworkers(coworkers: List<Coworker>): Completable
    fun getCoworkers(params: GetCoworkers.Params): Single<List<Coworker>>
}
