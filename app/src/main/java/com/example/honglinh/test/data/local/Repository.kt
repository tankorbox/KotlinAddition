package com.example.honglinh.test.data.local

import io.reactivex.Observable

/**
 * Created by HoàngLinh on 12/8/2017.
 */
abstract class Repository<T> {

    protected var mClassOfT: Class<T>

    constructor(mClassOfT: Class<T>) {
        this.mClassOfT = mClassOfT
    }

    open fun getAll(): Observable<List<T>>? {
        return null
    }

    open fun get(id: String) {

    }

    open fun get(id: Int) {

    }

    open fun add(entity: T, isUpdate: Boolean){

    }

    open fun addAll(collection: Collection<T>, isUpdate: Boolean) {

    }

    open fun delete(entity: T) {

    }
}