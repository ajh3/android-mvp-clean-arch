package com.aaronhalbert.androidmvpdemo.data.source

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerCache
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerDataStore
import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.GetCoworkers
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [CoworkerDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class CoworkerCacheDataStore @Inject constructor(private val coworkerCache: CoworkerCache) :
    CoworkerDataStore {

    override fun clearCoworkers(): Completable {
        return coworkerCache.clearCoworkers()
    }

    override fun saveCoworkers(coworkers: List<CoworkerEntity>): Completable {
        return coworkerCache.saveCoworkers(coworkers)
            .doOnComplete {
                coworkerCache.setLastCacheTime(System.currentTimeMillis())
            }
    }

    override fun getCoworkers(params: GetCoworkers.Params): Single<List<CoworkerEntity>> {
        return coworkerCache.getCoworkers(params.sortOrder)
    }
}
