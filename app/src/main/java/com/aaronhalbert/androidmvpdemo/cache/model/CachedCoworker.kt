package com.aaronhalbert.androidmvpdemo.cache.model

/**
 * Model used solely for the caching of a Coworker
 */
data class CachedCoworker(
    val firstName: String,
    val lastName: String,
    val title: String,
    val avatar: String,
    val bio: String,
    val id: Int
)
