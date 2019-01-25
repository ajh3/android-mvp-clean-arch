package com.aaronhalbert.androidmvpdemo.ui.mapper

import com.aaronhalbert.androidmvpdemo.presentation.model.CoworkerView
import com.aaronhalbert.androidmvpdemo.ui.model.CoworkerViewModel
import javax.inject.Inject

/**
 * Map a [CoworkerView] to and from a [CoworkerViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class CoworkerMapper @Inject constructor() : Mapper<CoworkerViewModel, CoworkerView> {
    override fun mapToViewModel(type: CoworkerView): CoworkerViewModel {
        return CoworkerViewModel(
            type.firstName,
            type.lastName,
            type.title,
            type.avatar,
            type.bio,
            type.id
        )
    }
}
