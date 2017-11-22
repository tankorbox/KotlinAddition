package com.example.mvvmadditionkotlin.view.adapter

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by HoàngLinh on 11/14/2017.
 */
abstract class TabAdapter<T>(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    var data: ObservableList<T>? = null
        protected set

    private val mCallback: WeakOnPagerChangedCallback<T> = WeakOnPagerChangedCallback(this)

    fun setData(items: Collection<T>?) {
        if (data === items) {
            return
        }
        if (data != null && !data!!.isEmpty()) {
            data!!.clear()
            data!!.removeOnListChangedCallback(mCallback)
            notifyDataSetChanged()
        }
        if (items is ObservableList<*>) {
            data = items as ObservableList<T>?
            data!!.addOnListChangedCallback(mCallback)
            notifyDataSetChanged()
        } else if (items != null) {
            data = ObservableArrayList()
            data!!.addAll(items)
            notifyDataSetChanged()
            data!!.addOnListChangedCallback(mCallback)
        } else {
            data = null
        }
    }

    override fun getCount(): Int {
        return if (data != null) data!!.size else 0
    }


}
