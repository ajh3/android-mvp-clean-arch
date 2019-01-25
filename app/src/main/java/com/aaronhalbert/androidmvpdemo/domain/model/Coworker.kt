package com.aaronhalbert.androidmvpdemo.domain.model

/**
 * Representation for a [Coworker] fetched from an external layer data source
 */
data class Coworker(
    val firstName: String,
    val lastName: String,
    val title: String,
    val avatar: String,
    val bio: String,
    val id: Int
)
