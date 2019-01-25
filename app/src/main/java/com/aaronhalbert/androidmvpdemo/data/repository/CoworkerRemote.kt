package com.aaronhalbert.androidmvpdemo.data.repository

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import io.reactivex.Single

/**
 * Interface defining methods for the remote downloading of Coworkers.
 * This is to be implemented by the remote layer, using this interface as a way of communicating.
 */
interface CoworkerRemote {

    fun getCoworkers(fileName: String): Single<List<CoworkerEntity>>
}
