package com.example.honglinh.test.data.local

import com.example.honglinh.test.model.User
import io.realm.RealmObject

/**
 * Created by HoàngLinh on 12/7/2017.
 */
interface IBaseRepository<T: RealmObject, S> {
    fun getAll(): List<T>?

    fun getAllSorted(fieldName: String): List<T>?

    fun getOneById(id: S): T?

    fun addOne(t: T)

    fun delOneById(id: S)

    fun addAll(listOfT: List<T>?)

    fun delAll()
}