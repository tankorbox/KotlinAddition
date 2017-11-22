package com.example.mvvmadditionkotlin.view

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by HoàngLinh on 11/14/2017.
 */

abstract class MVVMActivity<B : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    @JvmField @Inject
    var mViewModel: V? = null

    var toolbar: Toolbar? = null
        private set
    lateinit var viewDataBinding: B
        protected set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mViewModel != null) {
            mViewModel!!.onCreate()
        }
    }

    override fun onResume() {
        super.onResume()

        if (mViewModel != null) {
            mViewModel!!.onStart()
        }
    }

    override fun onPause() {
        if (mViewModel != null) {
            mViewModel!!.onStop()
        }

        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mViewModel != null) {
            mViewModel!!.onDestroy()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK && mViewModel != null) {
            mViewModel!!.onResult(requestCode, resultCode)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()

        if (mViewModel != null) {
            mViewModel!!.onLowMemory()
        }
    }

    //endregion

    //region Override method

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (mViewModel != null) {
                    mViewModel!!.navigator.goBack()
                } else {
                    finish()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //endregion

    //region Protected method

    protected fun setBindingContentView(layoutResId: Int, variableId: Int) {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResId)
        viewDataBinding.setVariable(variableId, mViewModel)
    }

    protected fun setToolbar(resId: Int, isEnabledHome: Boolean) {
        toolbar = findViewById(resId)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
        if (isEnabledHome) {
            val actionBar = supportActionBar
            if (actionBar != null) {
                actionBar.setHomeButtonEnabled(true)
                actionBar.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    protected fun setTitle(resId: Int, stringId: Int) {
        setTitle(resId, getString(stringId))
    }

    private fun setTitle(resId: Int, title: String) {
        setTitle("")

        val textView: TextView = findViewById(resId)
        if (textView != null) {
            textView.text = title
        }
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
        EventBus.getDefault()
                .post(`object`)
    }

    protected fun postSticky(`object`: Any) {
        EventBus.getDefault()
                .postSticky(`object`)
    }

    protected fun removeSticky(`object`: Any) {
        EventBus.getDefault().removeStickyEvent(`object`)
    }

    companion object {

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

}
