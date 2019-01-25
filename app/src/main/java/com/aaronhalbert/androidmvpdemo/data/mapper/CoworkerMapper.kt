package com.aaronhalbert.androidmvpdemo.data.mapper

import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import com.aaronhalbert.androidmvpdemo.domain.model.Coworker
import javax.inject.Inject

/**
 * Map a [CoworkerEntity] to and from a [Coworker] instance when data is moving between
 * this later and the Domain layer
 */
open class CoworkerMapper @Inject constructor() : Mapper<CoworkerEntity, Coworker> {

    override fun mapFromEntity(type: CoworkerEntity): Coworker {
        return Coworker(
            type.firstName,
            type.lastName,
            type.title,
            type.avatar,
            type.bio,
            type.id
        )
    }

    override fun mapToEntity(type: Coworker): CoworkerEntity {
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
