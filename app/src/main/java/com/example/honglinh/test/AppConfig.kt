package com.example.honglinh.test

import android.util.Log
import com.example.honglinh.test.di.component.AppComponent
import com.example.honglinh.test.di.component.DaggerAppComponent
import com.example.honglinh.test.utils.Const
import com.example.honglinh.test.view.MainActivity
import com.example.honglinh.test.view.PeopleActivity
import com.example.mvvmadditionkotlin.di.module.AppModule
import com.example.mvvmadditionkotlin.view.MVVMApplication
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.view.NavigatorImp
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class AppConfig: MVVMApplication() {

    @Inject
    lateinit var navigator: Navigator

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
        initPages()
    }

    private fun initPages() {
        appComponent.inject(this)

        Log.i("tag2", MainActivity::javaClass.javaClass.toString())


        navigator.configure(Const.MAIN_ACTIVITY, MainActivity::class.java)
        navigator.configure(Const.PEOPLE_ACTIVITY, PeopleActivity::class.java)

    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}