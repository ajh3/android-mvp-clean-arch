package com.aaronhalbert.androidmvpdemo.data.repository

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Coworkers. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface CoworkerCache {
    fun clearCoworkers(): Completable
    fun saveCoworkers(coworkers: List<CoworkerEntity>): Completable
    fun getCoworkers(sortOrder: BrowseActivity.SortOrder): Single<List<CoworkerEntity>>
    fun isCached(): Boolean
    fun setLastCacheTime(lastCache: Long)
    fun isExpired(): Boolean
}
