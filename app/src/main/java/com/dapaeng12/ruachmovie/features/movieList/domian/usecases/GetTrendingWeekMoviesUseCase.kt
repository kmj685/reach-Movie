package com.dapaeng12.ruachmovie.features.movieList.domian.usecases

import com.dapaeng12.ruachmovie.features.movieList.domian.entities.TmdbMovie
import com.dapaeng12.ruachmovie.features.movieList.domian.repositories.MovieListRepository

class GetTrendingWeekMoviesUseCase(
    private val repository: MovieListRepository
) {
    suspend operator fun invoke(): List<TmdbMovie> {
        return repository.getMovieWeekList()
    }
}