package com.example.honglinh.test.viewmodel.main.repos

import android.databinding.BaseObservable
import com.example.honglinh.test.model.Repository

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class RepoItemViewModel(private var repository: Repository) : BaseObservable() {
    val fullname: String
        get() = repository.full_name!!

    val createdTime: String
        get() = repository.created_at!!

    fun setRepository(repository: Repository) {
        this.repository = repository
        notifyChange()
    }

    fun onItemClick() {

    }
}