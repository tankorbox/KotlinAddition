package com.example.honglinh.test.data.remote.github

import com.example.honglinh.test.model.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by HoàngLinh on 12/6/2017.
 */
interface SearchService {
    @GET("search/users")
    fun searchUsers(@Query("q") name: String): Observable<SearchResult>
}