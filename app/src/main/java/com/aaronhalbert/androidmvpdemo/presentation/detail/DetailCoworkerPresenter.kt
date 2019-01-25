package com.aaronhalbert.androidmvpdemo.presentation.detail

import javax.inject.Inject

class DetailCoworkerPresenter @Inject constructor(
    private val detailView: DetailCoworkerContract.View
) : DetailCoworkerContract.Presenter {

    init {
        detailView.setPresenter(this)
    }

    override fun start() {
        detailView.showCoworker()
    }

    override fun stop() {}
}
