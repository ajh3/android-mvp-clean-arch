package com.aaronhalbert.androidmvpdemo.remote

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerRemote
import com.aaronhalbert.androidmvpdemo.remote.mapper.CoworkerEntityMapper
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote implementation for retrieving Coworker instances. This class implements
 * [CoworkerRemote] from the Data layer, as it is that layer's responsibility for defining the
 * operations which data store implementation layers can carry out.
 */
class CoworkerRemoteImpl @Inject constructor(
    private val coworkerService: CoworkerService,
    private val entityMapper: CoworkerEntityMapper
) : CoworkerRemote {

    override fun getCoworkers(fileName: String): Single<List<CoworkerEntity>> {
        return coworkerService.getCoworkers(fileName)
            .map { it.map { listItem -> entityMapper.mapFromRemote(listItem) } }
    }
}
