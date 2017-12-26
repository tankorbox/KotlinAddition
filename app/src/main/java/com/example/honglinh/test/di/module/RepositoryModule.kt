package com.example.honglinh.test.di.module

import com.example.honglinh.test.data.local.impl.EventRepositoryImp
import com.example.honglinh.test.data.local.impl.RepoRepositoryImp
import com.example.honglinh.test.data.local.impl.UserRepositoryImp
import dagger.Module
import dagger.Provides

/**
 * Created by HoàngLinh on 12/7/2017.
 */

@Module
class RepositoryModule {
    @Provides
    fun provideEventRepository() : EventRepositoryImp {
        return EventRepositoryImp()
    }

    @Provides
    fun provideRepoRepository(): RepoRepositoryImp {
        return RepoRepositoryImp()
    }

    @Provides
    fun provideUserRepository(): UserRepositoryImp {
        return UserRepositoryImp()
    }
}