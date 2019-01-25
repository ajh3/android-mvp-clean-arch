package com.aaronhalbert.androidmvpdemo.ui.injection.module

import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.ClearCoworkers
import com.aaronhalbert.androidmvpdemo.domain.interactor.browse.GetCoworkers
import com.aaronhalbert.androidmvpdemo.presentation.browse.BrowseCoworkersContract
import com.aaronhalbert.androidmvpdemo.presentation.browse.BrowseCoworkersPresenter
import com.aaronhalbert.androidmvpdemo.presentation.mapper.CoworkerMapper
import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity
import com.aaronhalbert.androidmvpdemo.ui.injection.scopes.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {

    @PerActivity
    @Provides
    fun provideBrowseView(browseActivity: BrowseActivity): BrowseCoworkersContract.View {
        return browseActivity
    }

    @PerActivity
    @Provides
    fun provideBrowsePresenter(
        mainView: BrowseCoworkersContract.View,
        getCoworkers: GetCoworkers,
        clearCoworkers: ClearCoworkers,
        mapper: CoworkerMapper
    ): BrowseCoworkersContract.Presenter {
        return BrowseCoworkersPresenter(mainView, getCoworkers, clearCoworkers, mapper)
    }
}
