package com.aaronhalbert.androidmvpdemo.presentation.mapper

import com.aaronhalbert.androidmvpdemo.domain.model.Coworker
import com.aaronhalbert.androidmvpdemo.presentation.model.CoworkerView
import javax.inject.Inject

/**
 * Map a [CoworkerView] to and from a [Coworker] instance when data is moving between
 * this layer and the Domain layer
 */
open class CoworkerMapper @Inject constructor() : Mapper<CoworkerView, Coworker> {

    override fun mapToView(type: Coworker): CoworkerView {
        return CoworkerView(
            type.firstName,
            type.lastName,
            type.title,
            type.avatar,
            type.bio,
            type.id
        )
    }
}
