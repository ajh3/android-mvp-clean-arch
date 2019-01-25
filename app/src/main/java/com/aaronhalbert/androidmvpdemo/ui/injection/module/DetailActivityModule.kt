package com.aaronhalbert.androidmvpdemo.ui.injection.module

import com.aaronhalbert.androidmvpdemo.presentation.detail.DetailCoworkerContract
import com.aaronhalbert.androidmvpdemo.presentation.detail.DetailCoworkerPresenter
import com.aaronhalbert.androidmvpdemo.ui.detail.DetailActivity
import com.aaronhalbert.androidmvpdemo.ui.injection.scopes.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class DetailActivityModule {

    @PerActivity
    @Provides
    fun provideDetailView(detailActivity: DetailActivity): DetailCoworkerContract.View {
        return detailActivity
    }

    @PerActivity
    @Provides
    fun provideDetailPresenter(
        mainView: DetailCoworkerContract.View
    ): DetailCoworkerContract.Presenter {
        return DetailCoworkerPresenter(mainView)
    }
}
