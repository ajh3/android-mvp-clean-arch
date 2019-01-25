package com.aaronhalbert.androidmvpdemo.cache.db.constants

import com.aaronhalbert.androidmvpdemo.cache.db.Db

/**
 * Defines DB queries for the Coworkers Table
 */
object CoworkerConstants {
    const val QUERY_GET_ALL_COWORKERS = "SELECT * FROM " + Db.CoworkerTable.TABLE_NAME

    const val QUERY_GET_ALL_COWORKERS_SORT_BY_FIRSTNAME_ASC =
        "SELECT * FROM " + Db.CoworkerTable.TABLE_NAME + " ORDER BY " + Db.CoworkerTable.FIRST_NAME + " ASC"

    const val QUERY_GET_ALL_COWORKERS_SORT_BY_FIRSTNAME_DESC =
        "SELECT * FROM " + Db.CoworkerTable.TABLE_NAME + " ORDER BY " + Db.CoworkerTable.FIRST_NAME + " DESC"

    const val QUERY_GET_ALL_COWORKERS_SORT_BY_LASTNAME_ASC =
        "SELECT * FROM " + Db.CoworkerTable.TABLE_NAME + " ORDER BY " + Db.CoworkerTable.LAST_NAME + " ASC"

    const val QUERY_GET_ALL_COWORKERS_SORT_BY_LASTNAME_DESC =
        "SELECT * FROM " + Db.CoworkerTable.TABLE_NAME + " ORDER BY " + Db.CoworkerTable.LAST_NAME + " DESC"

    const val QUERY_GET_ALL_COWORKERS_SORT_BY_ID_ASC =
        "SELECT * FROM " + Db.CoworkerTable.TABLE_NAME + " ORDER BY " + Db.CoworkerTable.COWORKER_ID + " ASC"

    const val QUERY_GET_ALL_COWORKERS_SORT_BY_ID_DESC =
        "SELECT * FROM " + Db.CoworkerTable.TABLE_NAME + " ORDER BY " + Db.CoworkerTable.COWORKER_ID + " DESC"
}
