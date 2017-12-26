package com.example.honglinh.test.viewmodel.main

import android.databinding.ObservableBoolean
import android.os.Handler
import com.example.honglinh.test.AppConfig
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class MainViewModel(navigator: Navigator) : @Inject ViewModel(navigator) {
    init {
        AppConfig.appComponent.inject(this)
    }

    private var handler: Handler = Handler()

    var isLoading: ObservableBoolean = ObservableBoolean(false)

    fun onTextChanged(name: CharSequence) {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            kotlin.run {
                post(name.toString())
            }
        }, 300)
    }

}