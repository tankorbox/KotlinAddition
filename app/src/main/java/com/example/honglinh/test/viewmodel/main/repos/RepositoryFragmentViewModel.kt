package com.example.honglinh.test.viewmodel.main.repos

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.data.local.impl.RepoRepositoryImp
import com.example.honglinh.test.data.remote.github.RepositoryService
import com.example.honglinh.test.model.Repository
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class RepositoryFragmentViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {
    @Inject
    lateinit var mRepositoryService: RepositoryService

    @Inject
    lateinit var mRepoRepository: RepoRepositoryImp

    private var mDisposable: Disposable? = null

    var repositories: ObservableList<Repository>? = ObservableArrayList()

    init {
        AppConfig.appComponent.inject(this)
        display(mRepoRepository.getAll())
    }


    fun fetchData() {
        mDisposable?.dispose()
        mDisposable = mRepositoryService.fetchRepositories()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repoList -> onSuccess(repoList) }, { onError() })
    }

    private fun onError() {
        navigator.showMessage("No Internet Connection")
    }

    private fun onSuccess(repoList: List<Repository>?) {
        mRepoRepository.run {
            delAll()
            addAll(repoList)
        }
        display(repoList)
    }

    private fun display(repoList: List<Repository>?) {
        repoList?.let {
            repositories?.clear()
            Log.i("tag1", "size display" + repoList.size)
            repositories?.addAll(repoList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }

    fun clearItem(adapterPosition: Int?) {
        adapterPosition?.let {
            repositories?.removeAt(adapterPosition)
        }
    }
}