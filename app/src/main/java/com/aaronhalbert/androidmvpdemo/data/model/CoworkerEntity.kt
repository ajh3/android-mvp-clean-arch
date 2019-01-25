package com.aaronhalbert.androidmvpdemo.data.model

/**
 * Representation for a [CoworkerEntity] fetched from an external layer data source
 */
data class CoworkerEntity(
    val firstName: String,
    val lastName: String,
    val title: String,
    val avatar: String,
    val bio: String,
    val id: Int
)
