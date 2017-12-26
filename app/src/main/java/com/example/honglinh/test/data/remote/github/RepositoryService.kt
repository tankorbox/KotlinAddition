package com.example.honglinh.test.data.remote.github

import com.example.honglinh.test.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by HoàngLinh on 12/7/2017.
 */

interface RepositoryService {
    @GET("users/tankorbox/repos")
    fun fetchRepositories(): Observable<List<Repository>>
}