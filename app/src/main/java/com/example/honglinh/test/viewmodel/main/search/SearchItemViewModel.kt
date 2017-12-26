package com.example.honglinh.test.viewmodel.main.search

import android.databinding.BaseObservable
import com.example.honglinh.test.model.User

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class SearchItemViewModel(private var user: User) : BaseObservable() {

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