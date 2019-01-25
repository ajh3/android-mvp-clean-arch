package com.aaronhalbert.androidmvpdemo.ui.injection.module

import android.app.Application
import android.content.Context
import com.aaronhalbert.androidmvpdemo.BuildConfig
import com.aaronhalbert.androidmvpdemo.cache.CoworkerCacheImpl
import com.aaronhalbert.androidmvpdemo.cache.PreferencesHelper
import com.aaronhalbert.androidmvpdemo.cache.db.DbOpenHelper
import com.aaronhalbert.androidmvpdemo.cache.mapper.CoworkerEntityMapper
import com.aaronhalbert.androidmvpdemo.data.CoworkerDataRepository
import com.aaronhalbert.androidmvpdemo.data.executor.JobExecutor
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerCache
import com.aaronhalbert.androidmvpdemo.data.repository.CoworkerRemote
import com.aaronhalbert.androidmvpdemo.data.source.CoworkerDataStoreFactory
import com.aaronhalbert.androidmvpdemo.domain.executor.PostExecutionThread
import com.aaronhalbert.androidmvpdemo.domain.executor.ThreadExecutor
import com.aaronhalbert.androidmvpdemo.domain.repository.CoworkerRepository
import com.aaronhalbert.androidmvpdemo.remote.CoworkerRemoteImpl
import com.aaronhalbert.androidmvpdemo.remote.CoworkerService
import com.aaronhalbert.androidmvpdemo.remote.CoworkerServiceFactory
import com.aaronhalbert.androidmvpdemo.ui.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideCoworkerRepository(
        factory: CoworkerDataStoreFactory,
        mapper: com.aaronhalbert.androidmvpdemo.data.mapper.CoworkerMapper
    ): CoworkerRepository {
        return CoworkerDataRepository(factory, mapper)
    }

    @Provides
    @Singleton
    fun provideCoworkerCache(
        factory: DbOpenHelper,
        entityMapper: CoworkerEntityMapper,
        mapper: com.aaronhalbert.androidmvpdemo.cache.db.mapper.CoworkerMapper,
        helper: PreferencesHelper
    ): CoworkerCache {
        return CoworkerCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @Singleton
    fun provideCoworkerRemote(
        service: CoworkerService,
        factory: com.aaronhalbert.androidmvpdemo.remote.mapper.CoworkerEntityMapper
    ): CoworkerRemote {
        return CoworkerRemoteImpl(service, factory)
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideCoworkerService(): CoworkerService {
        return CoworkerServiceFactory.makeCoworkerService(BuildConfig.DEBUG)
    }
}
