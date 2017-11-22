package com.example.honglinh.test.view

import android.os.Bundle
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.BR
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.ActivityMainBinding
import com.example.honglinh.test.viewmodel.MainViewModel
import com.example.mvvmadditionkotlin.view.MVVMActivity
import com.example.mvvmadditionkotlin.view.MVVMApplication
import javax.inject.Inject

class MainActivity : MVVMActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var mvvmApp: MVVMApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        AppConfig.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setBindingContentView(R.layout.activity_main, BR.viewmodel)
    }
}
