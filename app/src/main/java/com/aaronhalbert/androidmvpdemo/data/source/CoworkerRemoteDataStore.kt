package com.aaronhalbert.androidmvpdemo.data.source

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerDataStore
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerRemote
import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.GetCoworkers
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [CoworkerDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class CoworkerRemoteDataStore @Inject constructor(private val coworkerRemote: CoworkerRemote) :
    CoworkerDataStore {

    override fun clearCoworkers(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveCoworkers(coworkers: List<CoworkerEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun getCoworkers(params: GetCoworkers.Params): Single<List<CoworkerEntity>> {
        return coworkerRemote.getCoworkers(params.fileName)
    }
}
