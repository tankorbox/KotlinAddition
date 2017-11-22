package com.example.mvvmadditionkotlin.view

import android.app.Dialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/14/2017.
 */

open class MVVMDialogFragment<out B : ViewDataBinding,V : ViewModel> : DialogFragment() {
    private lateinit var mViewDataBinding: B

    @JvmField @Inject
    var mViewModel: V? = null

    fun getViewDataBinding(): B {
        return mViewDataBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel?.apply {
            mViewModel!!.onCreate()
        }
    }


    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.apply {
            dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return dialog
    }

    override fun onResume() {
        super.onResume()

        mViewModel?.apply {
            mViewModel!!.onStart()
        }
    }

    override fun onPause() {
        super.onPause()

        mViewModel?.apply {
            mViewModel!!.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mViewModel?.apply {
            mViewModel!!.onDestroy()
        }
    }

    protected fun setBindingContentView(inflater: LayoutInflater, container: ViewGroup, layoutResId: Int, variableId: Int) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        mViewDataBinding.setVariable(variableId, mViewModel)
    }

    protected fun scaleSize(dx: Float, dy: Float) {
        val width: Int = (resources.displayMetrics.widthPixels * dx).toInt()
        val height: Int = (resources.displayMetrics.heightPixels * dy).toInt()

        scaleSize(width, height)
    }

    private fun scaleSize(width: Int, height: Int) {
        val dialog: Dialog? = dialog
        if (dialog?.window != null) {
            dialog.window.setLayout(width, height)
        }
    }

    protected fun register() {
        val eventBus: EventBus = EventBus.getDefault()
        if (!eventBus.isRegistered(this)) {
            eventBus.unregister(this)
        }
    }

    protected fun unregister() {
        val eventBus: EventBus = EventBus.getDefault()
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this)
        }
    }

    protected fun post(any: Any) {
        EventBus.getDefault().post(any)
    }

    protected fun postSticky(any: Any) {
        EventBus.getDefault().postSticky(any)
    }

    protected fun removeSticky(any: Any) {
        EventBus.getDefault().removeStickyEvent(any)
    }
}