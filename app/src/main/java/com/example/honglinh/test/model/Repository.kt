package com.example.honglinh.test.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by HoàngLinh on 12/7/2017.
 */
open class Repository : RealmObject(){
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("full_name")
    var full_name: String? = null
    @SerializedName("private")
    var isPrivate: Boolean = false
    @SerializedName("url")
    var url: String? = null
    @SerializedName("branches_url")
    var branches_url: String? = null
    @SerializedName("created_at")
    var created_at: String? = null
    @SerializedName("updated_at")
    var updated_at: String? = null
    @SerializedName("pushed_at")
    var pushed_at: String? = null
    @SerializedName("size")
    var size: Int = 0
    @SerializedName("language")
    var language: String? = null
    @SerializedName("forks_count")
    var forks_count: Int = 0
    @SerializedName("default_branch")
    var default_branch: String? = null
}