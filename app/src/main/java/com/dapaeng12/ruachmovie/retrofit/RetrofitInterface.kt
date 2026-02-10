package com.dapaeng12.ruachmovie.retrofit

import com.dapaeng12.ruachmovie.utils.SplashAPI
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    // 예) http://www.unsplash.com/search/phtos/?query="$searchTerm"
    @GET(SplashAPI.SEARCH_PHOTOS)
    fun searchPhotos(@Query("query") searchTerm: String) : Call<JsonElement>

    @GET(SplashAPI.SEARCH_USERS)
    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>

}