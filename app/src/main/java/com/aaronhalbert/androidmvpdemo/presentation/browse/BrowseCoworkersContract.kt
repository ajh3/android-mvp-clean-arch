package com.aaronhalbert.androidmvpdemo.presentation.browse

import com.aaronhalbert.androidmvpdemo.presentation.BasePresenter
import com.aaronhalbert.androidmvpdemo.presentation.BaseView
import com.aaronhalbert.androidmvpdemo.presentation.model.CoworkerView
import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity.SortOrder

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseCoworkersContract {

    interface View : BaseView<Presenter> {
        val sortOrder: SortOrder
        val fileName: String

        fun showProgress()
        fun hideProgress()

        fun showCoworkers(coworkers: List<CoworkerView>)
        fun hideCoworkers()

        fun showErrorState()
        fun hideErrorState()

        fun showEmptyState()
        fun hideEmptyState()

        fun showMessage(message: String)

        fun clearSortOrder()
    }

    interface Presenter : BasePresenter {
        fun retrieveCoworkers()
        fun clearCoworkers(callback: () -> Unit = {})
        fun refresh()
    }
}
