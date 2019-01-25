package com.aaronhalbert.androidmvpdemo.presentation.detail

import com.aaronhalbert.androidmvpdemo.presentation.BasePresenter
import com.aaronhalbert.androidmvpdemo.presentation.BaseView

/**
 * Defines a contract of operations between the Detail Presenter and Detail View
 */
interface DetailCoworkerContract {

    interface View : BaseView<Presenter> {
        fun showProgress()
        fun hideProgress()
        fun showCoworker()
        fun hideCoworker()
        fun showErrorState()
        fun hideErrorState()
        fun showEmptyState()
        fun hideEmptyState()
    }

    interface Presenter : BasePresenter
}
