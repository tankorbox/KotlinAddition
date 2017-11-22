package com.example.mvvmadditionkotlin.view

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.multidex.MultiDexApplication
import java.lang.ref.SoftReference

/**
 * Created by HoàngLinh on 11/14/2017.
 */

abstract class MVVMApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

    private var mWeakActivity: SoftReference<Activity>? = null

    var currentActivity: Activity
        get() = mWeakActivity!!.get()!!
        set(activity) {
            if (mWeakActivity == null) {
                mWeakActivity = SoftReference(activity)
            }
            if (currentActivity !== activity) {
                mWeakActivity = SoftReference(activity)
            }
        }

    val isCurrentActivityAvailable: Boolean
        get() = mWeakActivity != null && mWeakActivity!!.get() != null

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

}
