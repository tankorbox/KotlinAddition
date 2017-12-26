package com.example.honglinh.test.viewmodel.main.user

import android.databinding.BaseObservable
import com.example.honglinh.test.model.User

/**
 * Created by HoàngLinh on 12/8/2017.
 */
class UserItemHorizontalViewModel(private var user: User) : BaseObservable() {
    val imageUrl: String?
        get() = user.avatar_url

    fun setUser(user: User) {
        this.user = user
        notifyChange()
    }

    fun onClick() {

    }
}