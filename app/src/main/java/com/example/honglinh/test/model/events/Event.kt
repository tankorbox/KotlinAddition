package com.example.honglinh.test.model.events

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by HoàngLinh on 12/4/2017.
 */

open class Event : RealmObject(){
    @PrimaryKey
    @SerializedName("id")
    var id: String = ""
    @SerializedName("type")
    var type: String? = null
    @SerializedName("actor")
    var actor: Actor? = null
    @SerializedName("repo")
    var repo: Repo? = null
    @SerializedName("public")
    var pub: Boolean = false
    @SerializedName("created_at")
    var created_at: String? = null

}
