package com.aaronhalbert.androidmvpdemo.remote.model

import com.aaronhalbert.androidmvpdemo.ui.browse.*
import com.google.gson.annotations.SerializedName

/**
 * Representation for a [CoworkerModel] fetched from the API
 *
 * Set up to let Kotlin work with Gson
 *
 * See https://proandroiddev.com/most-elegant-way-of-using-gson-kotlin-with-default-values-and-null-safety-b6216ac5328c
 *
 * TODO: replace Gson with Moshi
 */
data class CoworkerModel(
    @SerializedName(FIRST_NAME) val _firstName: String?,
    @SerializedName(LAST_NAME) val _lastName: String?,
    @SerializedName(TITLE) val _title: String?,
    @SerializedName(AVATAR) val _avatar: String?,
    @SerializedName(BIO) val _bio: String?,
    val id: Int = 0
) {
    val firstName
        get() = _firstName ?: ""

    val lastName
        get() = _lastName ?: ""

    val title
        get() = _title ?: ""

    val avatar
        get() = _avatar ?: ""

    val bio
        get() = _bio ?: ""
}
