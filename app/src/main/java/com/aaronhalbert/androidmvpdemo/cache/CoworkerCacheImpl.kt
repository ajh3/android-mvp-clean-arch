package com.aaronhalbert.androidmvpdemo.cache

import android.database.sqlite.SQLiteDatabase
import com.aaronhalbert.androidmvpdemo.cache.db.Db
import com.aaronhalbert.androidmvpdemo.cache.db.DbOpenHelper
import com.aaronhalbert.androidmvpdemo.cache.db.constants.CoworkerConstants
import com.aaronhalbert.androidmvpdemo.cache.db.mapper.CoworkerMapper
import com.aaronhalbert.androidmvpdemo.cache.mapper.CoworkerEntityMapper
import com.aaronhalbert.androidmvpdemo.cache.model.CachedCoworker
import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerCache
import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity.SortOrder
import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity.SortOrder.*
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Coworker instances. This class implements the
 * [CoworkerCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class CoworkerCacheImpl @Inject constructor(
    dbOpenHelper: DbOpenHelper,
    private val entityMapper: CoworkerEntityMapper,
    private val mapper: CoworkerMapper,
    private val preferencesHelper: PreferencesHelper
) : CoworkerCache {

    private val expTime = (60 * 10 * 1000).toLong()

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    override fun clearCoworkers(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.CoworkerTable.TABLE_NAME, null, null)
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    override fun saveCoworkers(coworkers: List<CoworkerEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                coworkers.forEach {
                    saveCoworker(entityMapper.mapToCached(it))
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    override fun getCoworkers(sortOrder: SortOrder): Single<List<CoworkerEntity>> {
        return Single.defer<List<CoworkerEntity>> {
            val query = when (sortOrder) {
                DEFAULT -> CoworkerConstants.QUERY_GET_ALL_COWORKERS
                FIRST_NAME_ASC -> CoworkerConstants.QUERY_GET_ALL_COWORKERS_SORT_BY_FIRSTNAME_ASC
                FIRST_NAME_DESC -> CoworkerConstants.QUERY_GET_ALL_COWORKERS_SORT_BY_FIRSTNAME_DESC
                LAST_NAME_ASC -> CoworkerConstants.QUERY_GET_ALL_COWORKERS_SORT_BY_LASTNAME_ASC
                LAST_NAME_DESC -> CoworkerConstants.QUERY_GET_ALL_COWORKERS_SORT_BY_LASTNAME_DESC
                ID_ASC -> CoworkerConstants.QUERY_GET_ALL_COWORKERS_SORT_BY_ID_ASC
                ID_DESC -> CoworkerConstants.QUERY_GET_ALL_COWORKERS_SORT_BY_ID_DESC
            }

            val updatesCursor = database.rawQuery(query, null)

            val coworkers = mutableListOf<CoworkerEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedCoworker = mapper.parseCursor(updatesCursor)
                coworkers.add(entityMapper.mapFromCached(cachedCoworker))
            }

            updatesCursor.close()
            Single.just<List<CoworkerEntity>>(coworkers)
        }
    }

    private fun saveCoworker(cachedCoworker: CachedCoworker) {
        database.insert(Db.CoworkerTable.TABLE_NAME, null, mapper.toContentValues(cachedCoworker))
    }

    override fun isCached(): Boolean {
        val cursor = database.rawQuery(CoworkerConstants.QUERY_GET_ALL_COWORKERS, null)
        val result = cursor.count > 0
        cursor.close()

        return result
    }

    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > expTime
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }
}
