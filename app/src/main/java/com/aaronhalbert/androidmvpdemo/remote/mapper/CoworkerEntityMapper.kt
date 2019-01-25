package com.aaronhalbert.androidmvpdemo.remote.mapper

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.remote.model.CoworkerModel
import javax.inject.Inject

/**
 * Map a [CoworkerModel] to and from a [CoworkerEntity] instance when data is moving between
 * this later and the Data layer
 */
open class CoworkerEntityMapper @Inject constructor() :
    EntityMapper<CoworkerModel, CoworkerEntity> {

    override fun mapFromRemote(type: CoworkerModel): CoworkerEntity {
        return CoworkerEntity(
            type.firstName,
            type.lastName,
            type.title,
            type.avatar,
            type.bio,
            type.id
        )
    }
}
