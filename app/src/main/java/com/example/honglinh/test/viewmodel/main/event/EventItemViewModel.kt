package com.example.honglinh.test.viewmodel.main.event

import android.databinding.BaseObservable
import com.example.honglinh.test.model.events.Event

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class EventItemViewModel(private var event: Event) : BaseObservable(){
    val id: String
        get() = event.id

    val type: String
        get() = event.type!!

    val createdTime : String
        get() = event.created_at!!

    fun setEvent(event: Event) {
        this.event = event
        notifyChange()
    }
    fun onItemClick() {

    }
}