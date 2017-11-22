package com.example.honglinh.test.viewmodel

import android.databinding.ObservableField
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class MainViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {
    var message: ObservableField<String> = ObservableField("hihi")


    fun goToPeopleActivity() {
        Log.i("tag1",navigator.getCurrentKey().toString())
        navigator.showPage(1)
    }
}