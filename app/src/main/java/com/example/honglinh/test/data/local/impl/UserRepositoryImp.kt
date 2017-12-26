package com.example.honglinh.test.data.local.impl

import com.example.honglinh.test.data.local.IUserRepository
import com.example.honglinh.test.model.User
import io.realm.Realm

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class UserRepositoryImp : IUserRepository {
    override fun getAll(): List<User>? {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(User::class.java).findAll()
        var copyResults: List<User>? = null
        if (results != null && !results.isEmpty()) {
            copyResults = realm.copyFromRealm(results)
        }
        return copyResults
    }

    override fun getAllSorted(fieldName: String): List<User>? {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(User::class.java).findAllSorted(fieldName)
        var copyResults: List<User>? = null
        if (results != null && !results.isEmpty()) {
            copyResults = realm.copyFromRealm(results)
        }
        realm.close()
        return copyResults
    }

    override fun getOneById(id: String): User? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(User::class.java).equalTo("id", id).findFirst()
        var copyResult: User? = null
        if (result != null) copyResult = realm.copyFromRealm(result)
        realm.close()
        return copyResult
    }

    override fun addOne(t: User) {
        val realm = Realm.getDefaultInstance()
        realm.copyToRealm(t)
        realm.close()
    }

    override fun delOneById(id: String) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(User::class.java).equalTo("id", id).findFirst()
        result.deleteFromRealm()
        realm.close()
    }

    override fun addAll(listOfT: List<User>?) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction({ transaction ->
            transaction.copyToRealmOrUpdate(listOfT)
        })
        realm.close()
    }

    override fun delAll() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction({ transaction ->
            transaction.delete(User::class.java)
        })
        realm.close()
    }

}