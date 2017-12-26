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
import com.example.honglinh.test.databinding.FragmentRepositoryBinding
import com.example.honglinh.test.view.adapter.RepositoryAdapter
import com.example.honglinh.test.viewmodel.main.repos.RepositoryFragmentViewModel
import com.example.mvvmadditionkotlin.view.MVVMFragment

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class RepositoryFragment: MVVMFragment<FragmentRepositoryBinding, RepositoryFragmentViewModel>(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        Handler().postDelayed({
            kotlin.run {
                viewModel?.fetchData()
                binding?.swipeRefreshLayout?.isRefreshing = false
            }
        }, 2000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppConfig.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setBindingContentView(inflater, container, R.layout.fragment_repository, BR.viewModel)
        setUpRepositoryList(binding?.repositoryRecyclerView)
        setUpRefreshLayout(binding?.swipeRefreshLayout)
        return binding?.root
    }

    private fun setUpRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout?) {
        swipeRefreshLayout?.setOnRefreshListener(this)
        swipeRefreshLayout?.setColorSchemeResources(R.color.red_primary, R.color.blue_primary, R.color.yellow_primary)
    }

    private fun setUpRepositoryList(repositoryRecyclerView: RecyclerView?) {
        repositoryRecyclerView?.let {
            repositoryRecyclerView.itemAnimator = DefaultItemAnimator()
            repositoryRecyclerView.layoutManager = LinearLayoutManager(context)
            repositoryRecyclerView.adapter = RepositoryAdapter()
            val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                    viewModel?.clearItem(viewHolder?.adapterPosition)
                }
            }
            ItemTouchHelper(callback).attachToRecyclerView(repositoryRecyclerView)
        }
    }
}