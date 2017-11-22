package com.example.honglinh.test.viewmodel

import android.databinding.ObservableField
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class MainViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {
    var message: ObservableField<String> = ObservableField("hihi")

}