package com.example.honglinh.test.model.events

import com.example.honglinh.test.model.User
import com.google.gson.annotations.SerializedName

/**
 * Created by HoàngLinh on 12/4/2017.
 */

open class Payload {
    @SerializedName("member")
    var user: User? = null

    @SerializedName("action")
    var action: String? = null

}
