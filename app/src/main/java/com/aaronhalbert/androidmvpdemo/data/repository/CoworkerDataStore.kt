package com.aaronhalbert.androidmvpdemo.data.repository

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.GetCoworkers
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Coworkers.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface CoworkerDataStore {

    fun clearCoworkers(): Completable
    fun saveCoworkers(coworkers: List<CoworkerEntity>): Completable
    fun getCoworkers(params: GetCoworkers.Params): Single<List<CoworkerEntity>>
}
