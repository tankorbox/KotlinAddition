package com.example.honglinh.test.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by HoàngLinh on 12/6/2017.
 */
open class User : RealmObject() {
    @Expose
    @SerializedName("login")
    var login: String = ""
    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Long = 0
    @Expose
    @SerializedName("avatar_url")
    var avatar_url: String? = null
    @Expose
    @SerializedName("gravatar_id")
    var gravatar_id: String? = null
    @Expose
    @SerializedName("url")
    var url: String? = null
    @Expose
    @SerializedName("html_url")
    var html_url: String? = null
    @Expose
    @SerializedName("followers_url")
    var followers_url: String? = null
    @Expose
    @SerializedName("following_url")
    var following_url: String? = null
    @Expose
    @SerializedName("gists_url")
    var gists_url: String? = null
}