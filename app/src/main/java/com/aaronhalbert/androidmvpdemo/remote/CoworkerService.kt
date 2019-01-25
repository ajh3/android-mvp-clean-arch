package com.aaronhalbert.androidmvpdemo.remote

import com.aaronhalbert.androidmvpdemo.remote.model.CoworkerModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the Coworker API
 */

interface CoworkerService {
    @GET("{fileName}")
    fun getCoworkers(@Path("fileName") fileName: String): Single<List<CoworkerModel>>
}
