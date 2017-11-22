package com.example.mvvmadditionkotlin.view.adapter

import android.support.annotation.Nullable

/**
 * Created by HoàngLinh on 11/14/2017.
 */
interface AdapterObserver<T> {

    fun setItems (@Nullable items: List<T>)

    fun getItems(): List<T>

}