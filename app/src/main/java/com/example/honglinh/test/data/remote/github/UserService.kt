package com.example.honglinh.test.data.remote.github

import com.example.honglinh.test.model.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by HoàngLinh on 12/6/2017.
 */
interface UserService {
    @GET("users")
    fun fetchUsers(): Observable<List<User>>
}