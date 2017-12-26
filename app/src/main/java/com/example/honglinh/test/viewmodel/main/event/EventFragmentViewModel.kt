package com.example.honglinh.test.viewmodel.main.event

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.data.local.impl.EventRepositoryImp
import com.example.honglinh.test.data.remote.github.EventService
import com.example.honglinh.test.model.events.Event
import com.example.mvvmadditionkotlin.view.Navigator
import com.example.mvvmadditionkotlin.viewmode.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class EventFragmentViewModel @Inject constructor(navigator: Navigator) : ViewModel(navigator) {
    @Inject
    lateinit var mEventService: EventService

    @Inject
    lateinit var mEventRepository: EventRepositoryImp

    var events: ObservableList<Event>? = ObservableArrayList()

    var mDisposable: Disposable? = null

    init {
        AppConfig.appComponent.inject(this)
        display(mEventRepository.getAll())
    }

    fun fetchData() {
        mDisposable?.dispose()
        mDisposable = mEventService.fetchEvents()
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, { onError() })
    }

    private fun onSuccess(listEvent: List<Event>) {
        mEventRepository.run {
            delAll()
            addAll(listEvent)
        }
        display(listEvent)
    }

    private fun onError() {
        navigator.showMessage("No internet connection")
    }

    private fun display(eventList: List<Event>?) {
        eventList?.let {
            events?.clear()
            events?.addAll(eventList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }

    fun clearItem(adapterPosition: Int?) {
        adapterPosition?.let {
            events?.removeAt(adapterPosition)
        }
    }
}