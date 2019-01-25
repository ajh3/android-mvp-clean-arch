package com.aaronhalbert.androidmvpdemo.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {
    object CoworkerTable {
        const val TABLE_NAME = "coworkers"
        const val COWORKER_ID = "coworker_id"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val TITLE = "title"
        const val AVATAR = "avatar"
        const val BIO = "bio"

        const val CREATE =
            "CREATE TABLE $TABLE_NAME ($COWORKER_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,$FIRST_NAME TEXT NOT NULL,$LAST_NAME TEXT NOT NULL,$TITLE TEXT,$AVATAR TEXT,$BIO TEXT);"
    }
}
