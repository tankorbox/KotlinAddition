package com.example.mvvmadditionkotlin.view.adapter

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import java.lang.ref.WeakReference

/**
 * Created by HoàngLinh on 11/14/2017.
 */

open class WeakOnListChangedCallback<T>(adapter: RecyclerView.Adapter<*>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {
    private val mWeakAdapter: WeakReference<RecyclerView.Adapter<*>> = WeakReference(adapter)

    override fun onItemRangeChanged(p0: ObservableList<T>?, p1: Int, p2: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is RecyclerView.Adapter) {
                mWeakAdapter.get()!!.notifyItemRangeChanged(p1,p2)
            }
        }
    }

    override fun onItemRangeInserted(p0: ObservableList<T>?, p1: Int, p2: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is RecyclerView.Adapter) {
                mWeakAdapter.get()!!.notifyItemRangeInserted(p1,p2)
            }
        }
    }

    override fun onItemRangeRemoved(p0: ObservableList<T>?, p1: Int, p2: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is RecyclerView.Adapter) {
                mWeakAdapter.get()!!.notifyItemRangeRemoved(p1,p2)
            }
        }
    }

    override fun onChanged(p0: ObservableList<T>?) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is RecyclerView.Adapter) {
                mWeakAdapter.get()!!.notifyDataSetChanged()
            }
        }
    }

    override fun onItemRangeMoved(p0: ObservableList<T>?, p1: Int, p2: Int, p3: Int) {
        mWeakAdapter.get()?.apply {
            if (mWeakAdapter.get() is RecyclerView.Adapter) {
                mWeakAdapter.get()!!.notifyItemMoved(p1,p2)
            }
        }
    }


}
