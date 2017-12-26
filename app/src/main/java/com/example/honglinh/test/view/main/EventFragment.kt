package com.example.honglinh.test.view.main

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.BR
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentEventBinding
import com.example.honglinh.test.view.adapter.EventAdapter
import com.example.honglinh.test.viewmodel.main.event.EventFragmentViewModel
import com.example.mvvmadditionkotlin.view.MVVMFragment

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class EventFragment : MVVMFragment<FragmentEventBinding, EventFragmentViewModel>(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        Handler().postDelayed({
            kotlin.run {
                viewModel?.fetchData()
                binding?.swipeRefreshLayout?.isRefreshing = false
            }
        }, 2000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppConfig.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setBindingContentView(inflater, container, R.layout.fragment_event, BR.viewModel)
        setUpEventList(binding?.rvEvents)
        setUpSwipeRefresh(binding?.swipeRefreshLayout)
        return binding?.root
    }

    private fun setUpSwipeRefresh(swipeRefreshLayout: SwipeRefreshLayout?) {
        swipeRefreshLayout?.let {
            swipeRefreshLayout.setColorSchemeResources(R.color.red_primary, R.color.blue_primary, R.color.yellow_primary)
            swipeRefreshLayout.setOnRefreshListener(this)
        }
    }

    private fun setUpEventList(rvEvents: RecyclerView?) {
        rvEvents?.let {
            rvEvents.layoutManager = LinearLayoutManager(context)
            rvEvents.itemAnimator = DefaultItemAnimator()
            rvEvents.adapter = EventAdapter()
            val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                    viewModel?.clearItem(viewHolder?.adapterPosition)
                }
            }
            ItemTouchHelper(callback).attachToRecyclerView(rvEvents)
        }
    }
}