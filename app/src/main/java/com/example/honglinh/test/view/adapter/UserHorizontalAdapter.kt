package com.example.honglinh.test.view.adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentUserHorizonItemBinding
import com.example.honglinh.test.model.User
import com.example.honglinh.test.viewmodel.main.user.UserItemHorizontalViewModel
import com.example.mvvmadditionkotlin.view.adapter.RecyclerViewAdapter
import com.example.mvvmadditionkotlin.view.adapter.ViewHolder

/**
 * Created by HoàngLinh on 12/8/2017.
 */
class UserHorizontalAdapter: RecyclerViewAdapter<User>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val itemBinding: FragmentUserHorizonItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_user_horizon_item, parent, false)
        return UserHorizontalViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder<*>?, position: Int) {
        val viewHolder: UserHorizontalViewHolder = holder as UserHorizontalViewHolder
        viewHolder.bindUser(getItems()[position])
    }

    class UserHorizontalViewHolder(private var itemBinding: FragmentUserHorizonItemBinding) : ViewHolder<FragmentUserHorizonItemBinding>(itemBinding) {
        fun bindUser(user: User) {
            if (itemBinding.viewModel==null) {
                itemBinding.viewModel = UserItemHorizontalViewModel(user)
            } else {
                itemBinding.viewModel?.setUser(user)
            }
        }
    }

}