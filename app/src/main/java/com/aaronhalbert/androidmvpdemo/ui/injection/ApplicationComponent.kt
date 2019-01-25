package com.aaronhalbert.androidmvpdemo.ui.injection

import android.app.Application
import com.aaronhalbert.androidmvpdemo.ui.CoworkerApplication
import com.aaronhalbert.androidmvpdemo.ui.injection.module.ActivityBuilder
import com.aaronhalbert.androidmvpdemo.ui.injection.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CoworkerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(app: CoworkerApplication)
}
