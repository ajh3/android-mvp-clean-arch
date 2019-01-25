package com.aaronhalbert.androidmvpdemo.ui.injection.module

import com.aaronhalbert.androidmvpdemo.ui.browse.BrowseActivity
import com.aaronhalbert.androidmvpdemo.ui.detail.DetailActivity
import com.aaronhalbert.androidmvpdemo.ui.injection.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [BrowseActivityModule::class])
    abstract fun bindBrowseActivity(): BrowseActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): DetailActivity
}
