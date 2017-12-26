package com.example.honglinh.test

import com.example.honglinh.test.di.component.AppComponent
import com.example.honglinh.test.di.component.DaggerAppComponent
import com.example.honglinh.test.di.module.CloudModule
import com.example.honglinh.test.di.module.RepositoryModule
import com.example.honglinh.test.di.module.ViewModelModule
import com.example.honglinh.test.utils.Const
import com.example.honglinh.test.view.MainActivity
import com.example.mvvmadditionkotlin.di.module.AppModule
import com.example.mvvmadditionkotlin.view.MVVMApplication
import com.example.mvvmadditionkotlin.view.Navigator
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class AppConfig : MVVMApplication() {

    @Inject
    lateinit var navigator: Navigator

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
        realmInit()
        initPages()
    }

    private fun realmInit() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun initPages() {
        appComponent.inject(this)
        navigator.configure(Const.MAIN_ACTIVITY, MainActivity::class.java)
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .cloudModule(CloudModule())
                .viewModelModule(ViewModelModule())
                .repositoryModule(RepositoryModule())
                .build()
    }
}