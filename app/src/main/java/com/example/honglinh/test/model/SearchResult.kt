package com.example.honglinh.test.model

import com.google.gson.annotations.SerializedName



/**
 * Created by HoàngLinh on 12/6/2017.
 */
open class SearchResult {
    @SerializedName("total_count")
    var total_count: Int = 0
    @SerializedName("incomplete_results")
    var incomplete_results: Boolean = false
    @SerializedName("items")
    var users: List<User>? = null
}