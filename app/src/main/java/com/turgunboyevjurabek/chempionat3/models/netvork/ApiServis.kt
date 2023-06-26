package com.turgunboyevjurabek.chempionat3.models.netvork

import com.turgunboyevjurabek.chempionat3.models.Valyuta
import retrofit2.Call
import retrofit2.http.GET

interface ApiServis {
    @GET("posts/")
    fun getData():Call<List<Valyuta>>

}