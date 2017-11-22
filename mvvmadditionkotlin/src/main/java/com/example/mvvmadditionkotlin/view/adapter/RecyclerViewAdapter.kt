package com.example.mvvmadditionkotlin.view.adapter

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView

/**
 * Created by HoàngLinh on 11/14/2017.
 */

abstract class RecyclerViewAdapter<T> : RecyclerView.Adapter<ViewHolder<*>>(), AdapterObserver<T> {

    private var mItems: ObservableList<T>? = null
    private val mCallback: WeakOnListChangedCallback<T> = WeakOnListChangedCallback(this)

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        if (mItems != null) {
            mItems!!.removeOnListChangedCallback(mCallback)
        }
    }

    override fun getItemCount(): Int {
        return if (mItems != null) mItems!!.size else 0
    }

    override fun setItems(items: List<T>?) {
        if (mItems === items) {
            return
        }
        if (mItems != null && !mItems!!.isEmpty()) {
            mItems!!.clear()
            mItems!!.removeOnListChangedCallback(mCallback)
            notifyDataSetChanged()
        }
        if (items is ObservableList<*>) {
            mItems = items as ObservableList<T>?
            notifyDataSetChanged()
            mItems!!.addOnListChangedCallback(mCallback)
        } else if (items != null) {
            mItems = ObservableArrayList()
            mItems!!.addAll(items)
            notifyDataSetChanged()
            mItems!!.addOnListChangedCallback(mCallback)
        } else {
            mItems = null
        }
    }

    override fun getItems(): List<T> {
        var list: List<T> = listOf()
        mItems?.apply {
            list = mItems!!.toList()
        }
        return list
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        @BindingAdapter(value = *arrayOf("items"))
        fun <T> setAdapter(recyclerView: RecyclerView, items: List<T>) {
            if (recyclerView.adapter is AdapterObserver<*>) {
                val adapter = recyclerView.adapter as AdapterObserver<T>
                adapter.setItems(items)
            }
        }
    }
}
