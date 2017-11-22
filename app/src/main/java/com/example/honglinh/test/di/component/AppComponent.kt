package com.example.honglinh.test.di.component

import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.view.MainActivity
import com.example.honglinh.test.view.PeopleActivity
import com.example.mvvmadditionkotlin.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by HoàngLinh on 11/22/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(peopleActivity: PeopleActivity)
    fun inject(appConfig: AppConfig)
}