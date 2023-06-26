package com.turgunboyevjurabek.chempionat3.models.netvork

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object  ApiClinet {
    val BASE_URL="https://jsonplaceholder.typicode.com/"

        //val BASE_URL="https://jsonplaceholder.typicode.com/posts"
    fun getRetrofit(context: Context): Retrofit {
            //logda ko'rib turish uchun
            val httpLoginInterceptor= HttpLoggingInterceptor()

            //log da body qismini chiqarip turadi
            httpLoginInterceptor.level= HttpLoggingInterceptor.Level.BODY

            val chuckInterceptor= ChuckInterceptor(context)

            val okHttpClient= OkHttpClient.Builder()
                .addInterceptor(httpLoginInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

        fun getRetrofitServis(context: Context):ApiServis{
            return getRetrofit(context).create(ApiServis::class.java)
        }
}