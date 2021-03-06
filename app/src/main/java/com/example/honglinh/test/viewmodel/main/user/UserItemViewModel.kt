package com.example.honglinh.test.viewmodel.main.user

import android.databinding.BaseObservable
import android.util.Log
import com.example.honglinh.test.model.User

/**
 * Created by HoàngLinh on 12/6/2017.
 */
open class UserItemViewModel(private var user: User) : BaseObservable() {

    fun getLogin(): String {
        return this.user.login
    }

    val avatarUrl: String
        get() = user.avatar_url!!

    val id: String
        get() = user.id.toString()

    val followersUrl: String
        get() = user.followers_url!!

    fun setUser(user: User) {
        this.user = user
        notifyChange()
    }

    fun onItemClick() {

    }
}