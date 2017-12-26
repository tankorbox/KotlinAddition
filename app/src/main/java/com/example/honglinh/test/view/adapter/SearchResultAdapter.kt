package com.example.honglinh.test.view.adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentSearchItemBinding
import com.example.honglinh.test.model.User
import com.example.honglinh.test.viewmodel.main.search.SearchItemViewModel
import com.example.mvvmadditionkotlin.view.adapter.RecyclerViewAdapter
import com.example.mvvmadditionkotlin.view.adapter.ViewHolder

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class SearchResultAdapter : RecyclerViewAdapter<User>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val itemBinding: FragmentSearchItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_search_item, parent, false)
        return SearchAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder<*>?, position: Int) {
        val viewHolder: SearchAdapterViewHolder = holder as SearchAdapterViewHolder
        viewHolder.bindUser(getItems()[position])
    }


    class SearchAdapterViewHolder(private var itemBinding: FragmentSearchItemBinding) : ViewHolder<FragmentSearchItemBinding>(itemBinding) {
        fun bindUser(user: User) {
            if (itemBinding.viewModel == null) {
                itemBinding.viewModel = SearchItemViewModel(user)
            } else {
                itemBinding.viewModel!!.setUser(user)
            }
        }
    }
}
