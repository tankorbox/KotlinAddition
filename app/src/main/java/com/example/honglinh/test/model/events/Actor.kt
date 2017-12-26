package com.example.honglinh.test.model.events

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject


/**
 * Created by HoàngLinh on 12/4/2017.
 */

open class Actor : RealmObject(){
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("login")
    var login: String? = null
    @SerializedName("display_login")
    var display_login: String? = null
    @SerializedName("gravatar_id")
    var gravatar_id: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("avatar_url")
    var avatar_url: String? = null
}
