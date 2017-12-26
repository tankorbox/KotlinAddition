package com.example.honglinh.test.di.module

import com.example.honglinh.test.data.remote.ServiceGenerator
import com.example.honglinh.test.data.remote.github.EventService
import com.example.honglinh.test.data.remote.github.RepositoryService
import com.example.honglinh.test.data.remote.github.SearchService
import com.example.honglinh.test.data.remote.github.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by HoàngLinh on 12/6/2017.
 */
@Module
class CloudModule {
    @Singleton
    @Provides
    fun provideUserService(): UserService {
        return ServiceGenerator.shared().createService(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchService(): SearchService {
        return ServiceGenerator.shared().createService(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepositoryService(): RepositoryService {
        return ServiceGenerator.shared().createService(RepositoryService::class.java)
    }

    @Singleton
    @Provides
    fun provideEventService() : EventService {
        return ServiceGenerator.shared().createService(EventService::class.java)
    }
}