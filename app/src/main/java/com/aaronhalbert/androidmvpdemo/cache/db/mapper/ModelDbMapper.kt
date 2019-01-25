package com.aaronhalbert.androidmvpdemo.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor

/**
 * Interface for database model mappers. It provides helper methods that facilitate
 * saving/retrieving java models into/from a relational database.
 *
 * @param <T> the model type
 */
interface ModelDbMapper<T> {

    fun toContentValues(model: T): ContentValues

    fun parseCursor(cursor: Cursor): T

}
