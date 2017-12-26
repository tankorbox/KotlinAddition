package com.example.honglinh.test.data.local.impl

import com.example.honglinh.test.data.local.IEventRepository
import com.example.honglinh.test.model.events.Event
import io.realm.Realm

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class EventRepositoryImp: IEventRepository {
    override fun getAll(): List<Event>? {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(Event::class.java).findAll()
        var copyResults: List<Event>? = null
        if (results != null && !results.isEmpty()) {
            copyResults = realm.copyFromRealm(results)
        }
        return copyResults
    }

    override fun getAllSorted(fieldName: String): List<Event>? {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(Event::class.java).findAllSorted(fieldName)
        var copyResults: List<Event>? = null
        if (results != null && !results.isEmpty()) {
            copyResults = realm.copyFromRealm(results)
        }
        return copyResults
    }

    override fun getOneById(id: String): Event? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(Event::class.java).equalTo("id", id).findFirst()
        var copyResult: Event? = null
        if (result != null) copyResult = realm.copyFromRealm(result)
        realm.close()
        return copyResult
    }

    override fun addOne(t: Event) {
        val realm = Realm.getDefaultInstance()
        realm.copyToRealm(t)
        realm.close()
    }

    override fun delOneById(id: String) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(Event::class.java).equalTo("id", id).findFirst()
        result.deleteFromRealm()
        realm.close()
    }

    override fun addAll(listOfT: List<Event>?) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction({ transaction ->
            transaction.copyToRealmOrUpdate(listOfT)
        })
        realm.close()
    }

    override fun delAll() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction({ transaction ->
            transaction.delete(Event::class.java)
        })
        realm.close()
    }

}