package com.example.mvvmadditionkotlin.di.module

import com.example.mvvmadditionkotlin.view.MVVMApplication
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.view.NavigatorImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by HoàngLinh on 11/14/2017.
 */
@Module
open class AppModule(private val mApplication: MVVMApplication) {

    @Provides
    fun provideApplication(): MVVMApplication {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return NavigatorImp(mApplication)
    }
}
