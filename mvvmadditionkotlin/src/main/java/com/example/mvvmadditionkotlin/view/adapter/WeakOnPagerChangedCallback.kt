package com.example.mvvmadditionkotlin.view.adapter

import android.databinding.ObservableList
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 * Created by HoàngLinh on 11/14/2017.
 */
open class WeakOnPagerChangedCallback<T>(adapter: PagerAdapter) : ObservableList.OnListChangedCallback<ObservableList<T>>() {
    override fun onItemRangeInserted(p0: ObservableList<T>?, p1: Int, p2: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is PagerAdapter) {
                mWeakAdapter.get()!!.notifyDataSetChanged()
            }
        }
    }

    override fun onChanged(p0: ObservableList<T>?) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is PagerAdapter) {
                mWeakAdapter.get()!!.notifyDataSetChanged()
            }
        }
    }

    override fun onItemRangeRemoved(p0: ObservableList<T>?, p1: Int, p2: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is PagerAdapter) {
                mWeakAdapter.get()!!.notifyDataSetChanged()
            }
        }
    }

    override fun onItemRangeChanged(p0: ObservableList<T>?, p1: Int, p2: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is PagerAdapter) {
                mWeakAdapter.get()!!.notifyDataSetChanged()
            }
        }
    }

    override fun onItemRangeMoved(p0: ObservableList<T>?, p1: Int, p2: Int, p3: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is PagerAdapter) {
                mWeakAdapter.get()!!.notifyDataSetChanged()
            }
        }
    }

    private val mWeakAdapter: WeakReference<PagerAdapter> = WeakReference(adapter)

}
