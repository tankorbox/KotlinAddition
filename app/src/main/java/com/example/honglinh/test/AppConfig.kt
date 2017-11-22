package com.example.honglinh.test

import android.util.Log
import com.example.honglinh.test.di.component.AppComponent
import com.example.honglinh.test.di.component.DaggerAppComponent
import com.example.mvvmadditionkotlin.di.module.AppModule
import com.example.mvvmadditionkotlin.view.MVVMApplication

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class AppConfig: MVVMApplication() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}