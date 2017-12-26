package com.example.honglinh.test.viewmodel.main.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.data.remote.github.SearchService
import com.example.honglinh.test.model.User
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class SearchFragmentViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {

    @Inject
    lateinit var mSearchService: SearchService

    private var mDisposable: Disposable? = null

    var isLoading: ObservableBoolean = ObservableBoolean(false)

    var users: ObservableList<User>? = ObservableArrayList<User>()

    init {
        AppConfig.appComponent.inject(this)
    }

    private fun fetchData(name: String) {
        isLoading.set(true)
        mDisposable?.dispose()
        mDisposable = mSearchService.searchUsers(name)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ results -> display(results.users) }, Throwable::printStackTrace)
    }

    private fun display(results: List<User>?) {
        isLoading.set(false)
        results?.let {
            users?.clear()
            users?.addAll(results)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onTextChange(name: String) {
        if (name != "") fetchData(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }

}