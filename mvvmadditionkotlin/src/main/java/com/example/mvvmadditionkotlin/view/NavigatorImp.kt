package com.example.mvvmadditionkotlin.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.mvvmadditionkotlin.R
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/14/2017.
 */

open class NavigatorImp @Inject constructor(private var mApplication: MVVMApplication) : Navigator {

    private var mBusyIndicator: BusyIndicator? = null
    private var mPages: SparseArray<Class<Any>> = SparseArray()

    override fun configure(pageKey: Int, pageClass: Class<Any>) {
        mPages.put(pageKey, pageClass)
    }

    override fun getApplication(): MVVMApplication {
        return mApplication
    }

    override fun getCurrentKey(): Int {
        val currentClass: Class<Any> = mApplication.currentActivity.javaClass
        val valueIndex = mPages.indexOfValue(currentClass)
        return mPages.keyAt(valueIndex)
    }

    override fun beginIgnoreEvent() {
        val activity: Activity? = getApplication().currentActivity
        activity?.apply {
            activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    override fun endIgnoreEvent() {
        val activity: Activity? = getApplication().currentActivity
        activity?.apply {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    override fun goBack() {
        goBack(false)
    }

    override fun goBack(isResultOk: Boolean) {
        if (mApplication.isCurrentActivityAvailable) {
            val currentActivity: Activity? = mApplication.currentActivity
            currentActivity?.apply {
                if (isResultOk) currentActivity.setResult(Activity.RESULT_OK)
            }
            ActivityCompat.finishAfterTransition(currentActivity)
        }
    }

    override fun showPage(pageKey: Int) {
        if (mApplication.isCurrentActivityAvailable) {
            val currentActivity: Activity? = mApplication.currentActivity
            currentActivity?.apply {
                val targetClass: Class<Any> = mPages.get(pageKey)
                val intent = Intent(currentActivity, targetClass)
                ActivityCompat.startActivity(currentActivity, intent, null)
            }
        }
    }

    override fun showPage(pageKey: Int, requestCode: Int) {
        if (mApplication.isCurrentActivityAvailable) {
            val currentActivity: Activity? = mApplication.currentActivity
            currentActivity?.apply {
                val targetClass: Class<Any> = mPages.get(pageKey)
                val intent = Intent(currentActivity, targetClass)
                ActivityCompat.startActivityForResult(currentActivity, intent, requestCode, null)
            }
        }
    }

    override fun showPage(pageKey: Int, bundle: Bundle) {
        if (mApplication.isCurrentActivityAvailable) {
            val currentActivity: Activity? = mApplication.currentActivity
            currentActivity?.apply {
                val targetClass: Class<Any> = mPages.get(pageKey)
                val intent = Intent(currentActivity, targetClass)
                intent.putExtras(bundle)
                ActivityCompat.startActivity(currentActivity, intent, null)
            }
        }
    }

    override fun showMessage(message: String) {
        val activity: Activity? = getApplication().currentActivity
        activity?.apply {

            val view: View = findViewById(R.id.coordinatorLayout)
            if (view is CoordinatorLayout) {
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .show()
                return
            }
        }
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(title: String, message: String, buttonText: String, callback: Callback) {
        if (mApplication.isCurrentActivityAvailable) {
            val currentActivity = mApplication.currentActivity
            currentActivity?.apply {
                AlertDialog.Builder(currentActivity)
                        .setMessage(message)
                        .setTitle(title)
                        .setNegativeButton(buttonText) { dialogInterface, _ ->
                            callback.onResult(false)
                            dialogInterface.dismiss()
                        }
                        .show()
            }
        }
    }

    override fun showMessage(title: String, message: String, buttonConfirmText: String, buttonCancelText: String, callback: Callback) {
        if (mApplication.isCurrentActivityAvailable) {
            val currentActivity = mApplication.currentActivity
            currentActivity?.apply {
                AlertDialog.Builder(currentActivity)
                        .setMessage(message)
                        .setTitle(title)
                        .setPositiveButton(buttonConfirmText) { dialogInterface, _ ->

                            callback.onResult(true)

                            dialogInterface.dismiss()
                        }
                        .setNegativeButton(buttonCancelText) { dialogInterface, _ ->

                            callback.onResult(false)

                            dialogInterface.dismiss()
                        }
                        .show()
            }
        }
    }

    override fun showBusyIndicator() {
        if (mApplication.isCurrentActivityAvailable) {
            val currentActivity = mApplication.currentActivity
            currentActivity?.apply {
                mBusyIndicator = BusyIndicator(currentActivity)
                mBusyIndicator!!.show()
            }
        }
    }

    override fun hideBusyIndicator() {
        mBusyIndicator?.apply {
            if (mBusyIndicator!!.isShowing) {
                try {
                    mBusyIndicator!!.dismiss()
                } catch (e: Exception) {
                    Log.e(NavigatorImp::class.java.name, e.message)
                } finally {
                    mBusyIndicator = null
                }
            }
        }
    }
}