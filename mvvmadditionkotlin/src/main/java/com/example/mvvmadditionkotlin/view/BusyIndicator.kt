package com.example.mvvmadditionkotlin.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import android.widget.TextView
import com.example.mvvmadditionkotlin.R

/**
 * Created by HoàngLinh on 11/14/2017.
 */
open class BusyIndicator : Dialog {

    private val mTextViewTitle: TextView? = null

    constructor(context: Context) : super(context) {

        initialize()
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {

        initialize()
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {

        initialize()
    }

    private fun initialize() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_busy)
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
    }

}
