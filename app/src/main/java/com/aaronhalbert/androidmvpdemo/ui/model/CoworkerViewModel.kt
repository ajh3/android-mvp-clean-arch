package com.aaronhalbert.androidmvpdemo.ui.model

/**
 * Representation for a [CoworkerViewModel] fetched from an external layer data source
 */
class CoworkerViewModel(
    val firstName: String,
    val lastName: String,
    val title: String,
    val avatar: String,
    val bio: String,
    val id: Int
)
