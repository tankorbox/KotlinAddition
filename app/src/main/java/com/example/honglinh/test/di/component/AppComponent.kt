package com.example.honglinh.test.di.component

import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.di.module.CloudModule
import com.example.honglinh.test.di.module.RepositoryModule
import com.example.honglinh.test.di.module.ViewModelModule
import com.example.honglinh.test.view.MainActivity
import com.example.honglinh.test.view.main.*
import com.example.honglinh.test.viewmodel.main.MainViewModel
import com.example.honglinh.test.viewmodel.main.event.EventFragmentViewModel
import com.example.honglinh.test.viewmodel.main.profile.ProfileFragmentViewModel
import com.example.honglinh.test.viewmodel.main.repos.RepositoryFragmentViewModel
import com.example.honglinh.test.viewmodel.main.search.SearchFragmentViewModel
import com.example.honglinh.test.viewmodel.main.user.UserFragmentViewModel
import com.example.mvvmadditionkotlin.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by HoàngLinh on 11/22/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, CloudModule::class, ViewModelModule::class, RepositoryModule::class))
interface AppComponent {
    //activities
    fun inject(mainActivity: MainActivity)

    //view model
    fun inject(mainViewModel: MainViewModel)

    fun inject(userFragmentViewModel: UserFragmentViewModel)

    fun inject(searchFragmentViewModel: SearchFragmentViewModel)

    fun inject(repositoryFragmentViewModel: RepositoryFragmentViewModel)

    fun inject(eventFragmentViewModel: EventFragmentViewModel)

    fun inject(profileFragmentViewModel: ProfileFragmentViewModel)

    //fragment
    fun inject(userFragment: UserFragment)

    fun inject(searchFragment: SearchFragment)
    fun inject(repositoryFragment: RepositoryFragment)
    fun inject(eventFragment: EventFragment)
    fun inject(profileFragment: ProfileFragment)

    //app
    fun inject(appConfig: AppConfig)


}