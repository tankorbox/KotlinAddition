package com.example.honglinh.test.viewmodel.main.user

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.util.Log
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.data.local.impl.UserRepositoryImp
import com.example.honglinh.test.data.remote.github.UserService
import com.example.honglinh.test.model.User
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class UserFragmentViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {

    private var mDisposable: Disposable? = null

    var users: ObservableList<User>? = ObservableArrayList<User>()

    var isLoading: ObservableBoolean = ObservableBoolean(false)

    @Inject
    lateinit var mUserService: UserService

    @Inject
    lateinit var mUserRepository: UserRepositoryImp

    init {
        Log.i("tag1", "init fragment model")
        AppConfig.appComponent.inject(this)
        display(mUserRepository.getAll())
    }

    fun fetchData() {
        isLoading.set(true)
        mDisposable?.dispose()
        mDisposable = mUserService.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, { onError() })
    }

    private fun onError() {
        isLoading.set(false)
        navigator.showMessage("No internet connection")
    }

    private fun onSuccess(userList: List<User>?) {
        isLoading.set(false)
        mUserRepository.delAll()
        mUserRepository.addAll(userList)
        display(userList)
    }

    private fun display(userList: List<User>?) {
        isLoading.set(false)
        userList?.let {
            users?.clear()
            users?.addAll(userList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }

    fun clearItem(adapterPosition: Int?) {
        users?.removeAt(adapterPosition!!)
    }
}