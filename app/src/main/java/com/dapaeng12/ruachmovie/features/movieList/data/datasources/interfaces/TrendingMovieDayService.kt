package com.dapaeng12.ruachmovie.features.movieList.data.datasources.interfaces

import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieListResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TrendingMovieDayService {
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMzQ2MWU2YzgzYmViYTc2YjM0Y2ZhNzQ0ZmM0NmE4NiIsIm5iZiI6MTc2ODc5OTg5NS4wNzYsInN1YiI6IjY5NmRiZTk3NmZkODg0NTgxYmYxMjM2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GWa3GakPZ3L8Mk9NS9CH0RfZYn7JFEdP__VDVCkvTEI",
        "accept: application/json"
    )
    @GET("trending/movie/day")
    suspend fun fetchTrendingMovieDay(
        @Query("language") language: String = "ko-KR"
    ) : TmdbMovieListResponseDto

}