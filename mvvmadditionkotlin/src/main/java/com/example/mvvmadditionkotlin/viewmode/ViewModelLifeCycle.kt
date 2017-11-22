package com.example.mvvmadditionkotlin.viewmode

/**
 * Created by HoàngLinh on 11/14/2017.
 */
interface ViewModelLifeCycle {

    fun onCreate()

    fun onStart()

    fun onStop()

    fun onDestroy()

    fun onResult(requestCode: Int, resultCode: Int)

    fun onLowMemory()

    fun onUserLeaveHint()
}