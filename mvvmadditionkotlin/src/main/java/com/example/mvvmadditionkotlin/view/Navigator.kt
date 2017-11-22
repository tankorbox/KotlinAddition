package com.example.mvvmadditionkotlin.view

import android.os.Bundle

/**
 * Created by HoàngLinh on 11/14/2017.
 */

interface Navigator {
    fun configure(pageKey: Int, pageClass: Class<*>)

    fun getApplication() : MVVMApplication

    fun getCurrentKey(): Int

    fun beginIgnoreEvent()

    fun endIgnoreEvent()

    fun goBack()

    fun goBack(isResultOk: Boolean)

    fun showPage(pageKey: Int)

    fun showPage(pageKey: Int,requestCode: Int)

    fun showPage(pageKey: Int, bundle: Bundle)

    fun showMessage(message: String)

    fun showMessage(title: String, message: String, buttonText: String, callback: Callback)

    fun showMessage(title: String, message: String, buttonConfirmText: String, buttonCancelText: String, callback: Callback)

    fun showBusyIndicator()

    fun hideBusyIndicator()
}