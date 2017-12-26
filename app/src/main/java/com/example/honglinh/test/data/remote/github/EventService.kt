package com.example.honglinh.test.data.remote.github

import com.example.honglinh.test.model.events.Event
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by HoàngLinh on 12/7/2017.
 */
interface EventService {
    @GET("events")
    fun fetchEvents(): Observable<List<Event>>
}