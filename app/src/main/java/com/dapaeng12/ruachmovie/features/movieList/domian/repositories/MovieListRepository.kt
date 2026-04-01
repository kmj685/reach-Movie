package com.dapaeng12.ruachmovie.features.movieList.domian.repositories

import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieDto

interface MovieListRepository {
    suspend fun getMovieDayList(): List<TmdbMovieDto>
    suspend fun getMovieWeekList(): List<TmdbMovieDto>
}