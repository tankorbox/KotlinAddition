package com.example.honglinh.test.view.adapter

import android.databinding.DataBindingUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentEventItemBinding
import com.example.honglinh.test.model.events.Event
import com.example.honglinh.test.viewmodel.main.event.EventItemViewModel
import com.example.mvvmadditionkotlin.view.adapter.RecyclerViewAdapter
import com.example.mvvmadditionkotlin.view.adapter.ViewHolder

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class EventAdapter : RecyclerViewAdapter<Event>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val itemBinding: FragmentEventItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_event_item, parent, false)
        return EventAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder<*>?, position: Int) {
        val viewHolder : EventAdapterViewHolder = holder as EventAdapterViewHolder
        viewHolder.bindEvents(getItems()[position])
    }

    class EventAdapterViewHolder(private var itemBinding: FragmentEventItemBinding) : ViewHolder<FragmentEventItemBinding>(itemBinding) {
        fun bindEvents(event: Event) {
            if (itemBinding.viewModel == null) {
                itemBinding.viewModel = EventItemViewModel(event)
            } else {
                itemBinding.viewModel?.setEvent(event)
            }
        }
    }
}