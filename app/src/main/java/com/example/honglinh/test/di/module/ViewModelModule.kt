package com.example.honglinh.test.di.module

import com.example.honglinh.test.viewmodel.main.MainViewModel
import com.example.honglinh.test.viewmodel.main.event.EventFragmentViewModel
import com.example.honglinh.test.viewmodel.main.profile.ProfileFragmentViewModel
import com.example.honglinh.test.viewmodel.main.repos.RepositoryFragmentViewModel
import com.example.honglinh.test.viewmodel.main.search.SearchFragmentViewModel
import com.example.honglinh.test.viewmodel.main.user.UserFragmentViewModel
import com.example.mvvmadditionkotlin.view.Navigator

import dagger.Module
import dagger.Provides

/**
 * Created by HoàngLinh on 12/6/2017.
 */

@Module
class ViewModelModule {
    @Provides
    fun provideMainViewModel(navigator: Navigator): MainViewModel {
        return MainViewModel(navigator)
    }

    @Provides
    fun provideUserFragmentViewModel(navigator: Navigator): UserFragmentViewModel {
        return UserFragmentViewModel(navigator)
    }

    @Provides
    fun provideSearchFragmentViewModel(navigator: Navigator) : SearchFragmentViewModel {
        return SearchFragmentViewModel(navigator)
    }

    @Provides
    fun provideRepositoryFragmentViewModel(navigator: Navigator): RepositoryFragmentViewModel {
        return RepositoryFragmentViewModel(navigator)
    }

    @Provides
    fun provideEventFragmentViewModel(navigator: Navigator): EventFragmentViewModel {
        return EventFragmentViewModel(navigator)
    }

    @Provides
    fun provideProfileFragmentViewModel(navigator: Navigator): ProfileFragmentViewModel {
        return ProfileFragmentViewModel(navigator)
    }
}
