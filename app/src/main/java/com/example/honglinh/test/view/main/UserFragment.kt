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
import com.example.honglinh.test.databinding.FragmentUserBinding
import com.example.honglinh.test.view.adapter.UserAdapter
import com.example.honglinh.test.view.adapter.UserHorizontalAdapter
import com.example.honglinh.test.viewmodel.main.user.UserFragmentViewModel
import com.example.mvvmadditionkotlin.view.MVVMFragment

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class UserFragment : MVVMFragment<FragmentUserBinding, UserFragmentViewModel>(), SwipeRefreshLayout.OnRefreshListener {

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
        setBindingContentView(inflater, container, R.layout.fragment_user, BR.viewModel)
        setUpListUser(binding!!.userRecyclerView)
        setUListHorizontalAvatar(binding!!.userRecyclerViewHorizontal)
        setUpSwipeRefresh(binding!!.swipeRefreshLayout)
        return binding?.root
    }

    private fun setUListHorizontalAvatar(userRecyclerViewHorizontal: RecyclerView) {
        userRecyclerViewHorizontal.itemAnimator = DefaultItemAnimator()
        userRecyclerViewHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        userRecyclerViewHorizontal.adapter = UserHorizontalAdapter()
    }

    private fun setUpSwipeRefresh(swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeResources(R.color.red_primary, R.color.yellow_primary, R.color.blue_primary)
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun setUpListUser(userRecyclerView: RecyclerView) {
        userRecyclerView.itemAnimator = DefaultItemAnimator()
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.adapter = UserAdapter()

        val simpleCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                viewModel?.clearItem(viewHolder?.adapterPosition)
            }
        }

        ItemTouchHelper(simpleCallBack).attachToRecyclerView(userRecyclerView)
    }
}