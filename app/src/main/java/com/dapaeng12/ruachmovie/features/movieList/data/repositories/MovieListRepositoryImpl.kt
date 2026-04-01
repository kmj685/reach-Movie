package com.dapaeng12.ruachmovie.features.movieList.data.repositories

import com.dapaeng12.ruachmovie.features.movieList.data.datasources.RetrofitManager
import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieDto
import com.dapaeng12.ruachmovie.features.movieList.domian.repositories.MovieListRepository

class MovieListRepositoryImpl : MovieListRepository {
    override suspend fun getMovieDayList(): List<TmdbMovieDto> {
        val response = RetrofitManager.trendingMovieDayService.fetchTrendingMovieDay()
        return response.results
    }

    override suspend fun getMovieWeekList(): List<TmdbMovieDto> {
        val response = RetrofitManager.trendingMovieWeekService.fetchTrendingMovieWeek()
        return response.results
    }
}