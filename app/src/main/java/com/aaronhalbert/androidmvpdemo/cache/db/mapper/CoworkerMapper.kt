package com.aaronhalbert.androidmvpdemo.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor
import com.aaronhalbert.androidmvpdemo.cache.db.Db
import com.aaronhalbert.androidmvpdemo.cache.model.CachedCoworker
import javax.inject.Inject

/**
 * Maps a [CachedCoworker] instance to a database entity.
 */
class CoworkerMapper @Inject constructor() : ModelDbMapper<CachedCoworker> {

    override fun toContentValues(model: CachedCoworker): ContentValues {
        val values = ContentValues()
        values.put(Db.CoworkerTable.FIRST_NAME, model.firstName)
        values.put(Db.CoworkerTable.LAST_NAME, model.lastName)
        values.put(Db.CoworkerTable.TITLE, model.title)
        values.put(Db.CoworkerTable.AVATAR, model.avatar)
        values.put(Db.CoworkerTable.BIO, model.bio)
        values.put(Db.CoworkerTable.COWORKER_ID, model.id)
        return values
    }

    override fun parseCursor(cursor: Cursor): CachedCoworker {
        val firstName = cursor.getString(cursor.getColumnIndexOrThrow(Db.CoworkerTable.FIRST_NAME))
        val lastName = cursor.getString(cursor.getColumnIndexOrThrow(Db.CoworkerTable.LAST_NAME))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(Db.CoworkerTable.TITLE))
        val avatar = cursor.getString(cursor.getColumnIndexOrThrow(Db.CoworkerTable.AVATAR))
        val bio = cursor.getString(cursor.getColumnIndexOrThrow(Db.CoworkerTable.BIO))
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.CoworkerTable.COWORKER_ID))
        return CachedCoworker(firstName, lastName, title, avatar, bio, id)
    }
}
