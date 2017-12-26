package com.example.honglinh.test.view.adapter

import android.databinding.DataBindingUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentUserListItemBinding
import com.example.honglinh.test.model.User
import com.example.honglinh.test.viewmodel.main.user.UserItemViewModel
import com.example.mvvmadditionkotlin.view.adapter.RecyclerViewAdapter
import com.example.mvvmadditionkotlin.view.adapter.ViewHolder

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class UserAdapter : RecyclerViewAdapter<User>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val itemBinding: FragmentUserListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_user_list_item, parent, false)
        return UserAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder<*>?, position: Int) {
        val viewHolder: UserAdapterViewHolder = holder as UserAdapterViewHolder
        viewHolder.bindUser(getItems()[position])
    }


    class UserAdapterViewHolder(private var itemBinding: FragmentUserListItemBinding) : ViewHolder<FragmentUserListItemBinding>(itemBinding) {
        fun bindUser(user: User) {
            if (itemBinding.viewModel == null) {
                itemBinding.viewModel = UserItemViewModel(user)
            } else {
                itemBinding.viewModel?.setUser(user)
            }
        }
    }
}
