package com.aaronhalbert.androidmvpdemo.cache.mapper

import com.aaronhalbert.androidmvpdemo.cache.model.CachedCoworker
import com.aaronhalbert.androidmvpdemo.data.model.CoworkerEntity
import javax.inject.Inject

/**
 * Map a [CachedCoworker] instance to and from a [CoworkerEntity] instance when data is moving
 * between this later and the Data layer
 */
class CoworkerEntityMapper @Inject constructor() : EntityMapper<CachedCoworker, CoworkerEntity> {

    override fun mapToCached(type: CoworkerEntity): CachedCoworker {
        return CachedCoworker(
            type.firstName,
            type.lastName,
            type.title,
            type.avatar,
            type.bio,
            type.id
        )
    }

    override fun mapFromCached(type: CachedCoworker): CoworkerEntity {
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
