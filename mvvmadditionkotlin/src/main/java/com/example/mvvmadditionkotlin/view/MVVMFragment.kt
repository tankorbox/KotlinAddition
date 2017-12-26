package com.example.mvvmadditionkotlin.view

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import android.widget.Toast
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by HoàngLinh on 11/14/2017.
 */

abstract class MVVMFragment<B : ViewDataBinding, V : ViewModel> : Fragment() {
    @JvmField
    @Inject
    var viewModel: V? = null

    var binding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        viewModel?.onCreate()
    }

    override fun onResume() {
        super.onResume()

        viewModel?.onStart()
    }

    override fun onPause() {
        super.onPause()

        viewModel?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel?.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK && viewModel != null) {
            viewModel!!.onResult(requestCode, resultCode)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()

        viewModel?.onLowMemory()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
        super.onPrepareOptionsMenu(menu)
    }

    protected fun setBindingContentView(inflater: LayoutInflater, container: ViewGroup?, layoutResId: Int, variableId: Int) {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding?.setVariable(variableId, viewModel)
    }

    protected fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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

    protected fun post(any: Any) {
        EventBus.getDefault()
                .post(any)
    }

    protected fun postSticky(any: Any) {
        EventBus.getDefault()
                .postSticky(any)
    }

    protected fun removeSticky(any: Any) {
        EventBus.getDefault().removeStickyEvent(any)
    }

}
