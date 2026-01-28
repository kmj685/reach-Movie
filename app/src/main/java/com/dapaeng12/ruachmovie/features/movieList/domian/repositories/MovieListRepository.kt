package com.dapaeng12.ruachmovie.features.movieList.domian.repositories

import com.dapaeng12.ruachmovie.features.movieList.domian.entities.TmdbMovie

interface MovieListRepository {
    suspend fun getMovieDayList(): List<TmdbMovie>
    suspend fun getMovieWeekList(): List<TmdbMovie>
}