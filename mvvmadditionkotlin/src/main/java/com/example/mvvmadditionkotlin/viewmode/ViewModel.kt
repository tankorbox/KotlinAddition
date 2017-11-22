package com.example.mvvmadditionkotlin.viewmode

import android.content.res.Resources
import android.databinding.BaseObservable
import com.example.mvvmadditionkotlin.view.MVVMApplication
import com.example.mvvmadditionkotlin.view.Navigator
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/14/2017.
 */


open class ViewModel @Inject constructor(val navigator: Navigator) : BaseObservable(), ViewModelLifeCycle {

    val resources: Resources
        get() = navigator.getApplication().resources


    val application: MVVMApplication
        get() = navigator.getApplication()

    override fun onCreate() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onDestroy() {

    }

    override fun onResult(requestCode: Int, resultCode: Int) {

    }

    override fun onLowMemory() {

    }

    override fun onUserLeaveHint() {

    }

    protected fun register() {
        val eventBus = EventBus.getDefault()
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this)
        }
    }

    protected fun unregister() {
        val eventBus = EventBus.getDefault()
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this)
        }
    }

    protected fun post(`object`: Any) {
        EventBus.getDefault().post(`object`)
    }

    protected fun postSticky(`object`: Any) {
        EventBus.getDefault().postSticky(`object`)
    }

    protected fun removeSticky(`object`: Any) {
        EventBus.getDefault().removeStickyEvent(`object`)
    }

}
