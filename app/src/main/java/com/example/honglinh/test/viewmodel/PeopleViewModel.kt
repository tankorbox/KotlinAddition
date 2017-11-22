package com.example.honglinh.test.viewmodel

import android.databinding.ObservableField
import android.util.Log
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class PeopleViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {
    var message: ObservableField<String> = ObservableField("hihi")


    fun goBack() {
        Log.i("tag1","goback")
        navigator.goBack()
    }
}