package com.example.honglinh.test.view.adapter

import android.databinding.DataBindingUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentRepositoryItemBinding
import com.example.honglinh.test.model.Repository
import com.example.honglinh.test.viewmodel.main.repos.RepoItemViewModel
import com.example.mvvmadditionkotlin.view.adapter.RecyclerViewAdapter
import com.example.mvvmadditionkotlin.view.adapter.ViewHolder

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class RepositoryAdapter : RecyclerViewAdapter<Repository>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val itemBinding: FragmentRepositoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_repository_item, parent, false)
        return RepositoryAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder<*>?, position: Int) {
        val viewHolder: RepositoryAdapterViewHolder = holder as RepositoryAdapterViewHolder
        viewHolder.bindRepository(getItems()[position])
    }

    class RepositoryAdapterViewHolder(private var itemBinding: FragmentRepositoryItemBinding) : ViewHolder<FragmentRepositoryItemBinding>(itemBinding) {
        fun bindRepository(repository: Repository) {
            if (itemBinding.viewModel == null) {
                itemBinding.viewModel = RepoItemViewModel(repository)
            } else {
                itemBinding.viewModel!!.setRepository(repository)
            }
        }
    }
}