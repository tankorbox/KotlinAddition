package com.example.mvvmadditionkotlin.view.adapter

/**
 * Created by HoàngLinh on 11/14/2017.
 */
interface AdapterObserver<T> {

    fun setItems(items: List<T>?)

    fun getItems(): List<T>

}