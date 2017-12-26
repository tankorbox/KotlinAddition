package com.example.honglinh.test.model.events

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by HoàngLinh on 12/4/2017.
 */

open class Repo : RealmObject(){
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
}
