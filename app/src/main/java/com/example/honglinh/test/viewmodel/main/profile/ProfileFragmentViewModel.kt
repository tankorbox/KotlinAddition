package com.example.honglinh.test.viewmodel.main.profile

import com.example.honglinh.test.AppConfig
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import javax.inject.Inject

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class ProfileFragmentViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {
    init {
        AppConfig.appComponent.inject(this)
    }
}