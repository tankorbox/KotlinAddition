package com.example.mvvmadditionkotlin.view

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import android.widget.Toast
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import org.greenrobot.eventbus.EventBus

/**
 * Created by HoàngLinh on 11/14/2017.
 */

abstract class MVVMFragment<B : ViewDataBinding, V : ViewModel> : android.support.v4.app.Fragment() {
    var viewModel: V? = null
        protected set
    var binding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        viewModel?.apply {
            viewModel!!.onCreate()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel?.apply {
            viewModel!!.onStart()
        }
    }

    override fun onPause() {
        super.onPause()

        viewModel?.apply {
            viewModel!!.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel?.apply {
            viewModel!!.onDestroy()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK && viewModel != null) {
            viewModel!!.onResult(requestCode, resultCode)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()

        viewModel?.apply {
            viewModel!!.onLowMemory()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
        super.onPrepareOptionsMenu(menu)
    }

    protected fun setBindingContentView(inflater: LayoutInflater, container: ViewGroup?, layoutResId: Int, variableId: Int) {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding?.apply {
            binding!!.setVariable(variableId, viewModel)
        }
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
