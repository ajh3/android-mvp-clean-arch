package com.aaronhalbert.androidmvpdemo.data

import com.aaronhalbert.androidmvpdemo.data.mapper.CoworkerMapper
import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.data.source.CoworkerDataStoreFactory
import com.aaronhalbert.androidmvpdemo.data.source.CoworkerRemoteDataStore
import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.GetCoworkers
import com.aaronhalbert.androidmvpdemo.domain.model.Coworker
import com.aaronhalbert.androidmvpdemo.domain.repository.CoworkerRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [CoworkerRepository] interface for communicating to and from
 * data sources
 */
class CoworkerDataRepository @Inject constructor(
    private val factory: CoworkerDataStoreFactory,
    private val coworkerMapper: CoworkerMapper
) : CoworkerRepository {

    override fun clearCoworkers(): Completable {
        return factory.retrieveCacheDataStore().clearCoworkers()
    }

    override fun saveCoworkers(coworkers: List<Coworker>): Completable {
        val coworkerEntities = coworkers.map { coworkerMapper.mapToEntity(it) }
        return saveCoworkerEntities(coworkerEntities)
    }

    private fun saveCoworkerEntities(coworkers: List<CoworkerEntity>): Completable {
        return factory.retrieveCacheDataStore().saveCoworkers(coworkers)
    }

    override fun getCoworkers(params: GetCoworkers.Params): Single<List<Coworker>> {
        return factory.retrieveDataStore()
            .let { dataStore ->
                dataStore.getCoworkers(params)
                    .flatMap {
                        if (dataStore is CoworkerRemoteDataStore) {
                            saveCoworkerEntities(it).toSingle { it }
                        } else {
                            Single.just(it)
                        }
                    }
                    .map { list -> list.map { coworkerMapper.mapFromEntity(it) } }
            }
    }
}
