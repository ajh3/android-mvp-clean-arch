package com.aaronhalbert.androidmvpdemo.cache

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */

private const val PREF_PACKAGE_NAME = "com.aaronhalbert.androidmvpdemo.preferences"
private const val PREF_KEY_LAST_CACHE = "last_cache"
private const val DEF_VALUE: Long = 0

@Singleton
class PreferencesHelper @Inject constructor(context: Context) {

    private val mvpDemoPref: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var lastCacheTime: Long
        get() = mvpDemoPref.getLong(PREF_KEY_LAST_CACHE, DEF_VALUE)
        set(lastCache) = mvpDemoPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}
