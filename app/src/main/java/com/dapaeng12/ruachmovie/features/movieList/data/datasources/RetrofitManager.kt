package com.dapaeng12.ruachmovie.features.movieList.data.datasources

import com.dapaeng12.ruachmovie.features.movieList.data.datasources.interfaces.TrendingMovieDayService
import com.dapaeng12.ruachmovie.features.movieList.data.datasources.interfaces.TrendingMovieWeekService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var trendingMovieDayService: TrendingMovieDayService = retrofit.create<TrendingMovieDayService>(TrendingMovieDayService::class.java)
    var trendingMovieWeekService: TrendingMovieWeekService = retrofit.create<TrendingMovieWeekService>(TrendingMovieWeekService::class.java)
}