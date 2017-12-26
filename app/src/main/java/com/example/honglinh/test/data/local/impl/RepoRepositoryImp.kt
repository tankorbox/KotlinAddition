package com.example.honglinh.test.data.local.impl

import com.example.honglinh.test.data.local.IRepoRepository
import com.example.honglinh.test.model.Repository
import com.example.honglinh.test.model.events.Event
import io.realm.Realm

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class RepoRepositoryImp : IRepoRepository {
    override fun getAll(): List<Repository>? {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(Repository::class.java).findAll()
        var copyResults: List<Repository>? = null
        if (results != null && !results.isEmpty()) {
            copyResults = realm.copyFromRealm(results)
        }
        return copyResults
    }

    override fun getAllSorted(fieldName: String): List<Repository>? {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(Repository::class.java).findAllSorted(fieldName)
        var copyResults: List<Repository>? = null
        if (results != null && !results.isEmpty()) {
            copyResults = realm.copyFromRealm(results)
        }
        return copyResults
    }

    override fun getOneById(id: Int): Repository? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(Repository::class.java).equalTo("id", id).findFirst()
        var copyResult: Repository? = null
        if (result != null) copyResult = realm.copyFromRealm(result)
        realm.close()
        return copyResult
    }

    override fun addOne(t: Repository) {
        val realm = Realm.getDefaultInstance()
        realm.copyToRealm(t)
        realm.close()
    }

    override fun delOneById(id: Int) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(Repository::class.java).equalTo("id", id).findFirst()
        result.deleteFromRealm()
        realm.close()
    }

    override fun addAll(listOfT: List<Repository>?) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction({ transaction ->
            transaction.copyToRealmOrUpdate(listOfT)
        })
        realm.close()
    }

    override fun delAll() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction({ transaction ->
            transaction.delete(Repository::class.java)
        })
        realm.close()
    }

}