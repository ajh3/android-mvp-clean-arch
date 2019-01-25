package com.aaronhalbert.androidmvpdemo.data.source

import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerCache
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerDataStore
import javax.inject.Inject

/**
 * Create an instance of a CoworkerDataStore
 */
open class CoworkerDataStoreFactory @Inject constructor(
    private val coworkerCache: CoworkerCache,
    private val coworkerCacheDataStore: CoworkerCacheDataStore,
    private val coworkerRemoteDataStore: CoworkerRemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): CoworkerDataStore {
        if (coworkerCache.isCached() && !coworkerCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    open fun retrieveCacheDataStore(): CoworkerDataStore {
        return coworkerCacheDataStore
    }

    open fun retrieveRemoteDataStore(): CoworkerDataStore {
        return coworkerRemoteDataStore
    }
}
