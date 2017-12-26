package com.example.honglinh.test.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by HoàngLinh on 12/6/2017.
 */

class ServiceGenerator private constructor() {

    private val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthenticationInterceptor("c7e26d6f4c97aaf4b40407b6778941117f0556a1"))
            .build()

    private val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }


    class AuthenticationInterceptor(private val authToken: String) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

            val builder = original.newBuilder()
                    .header("Authorization", "token " + authToken)

            val request = builder.build()
            return chain.proceed(request)
        }
    }

    companion object {

        val BASE_URL = "https://api.github.com/"

        private var generator: ServiceGenerator? = null

        @Synchronized
        fun shared(): ServiceGenerator {
            if (generator == null) {
                generator = ServiceGenerator()
            }
            return generator as ServiceGenerator
        }
    }

}
