package com.dapaeng12.ruachmovie.features.movieList.domian.usecases

import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieDto
import com.dapaeng12.ruachmovie.features.movieList.domian.repositories.MovieListRepository

class GetTrendingWeekMoviesUseCase(
    private val repository: MovieListRepository
) {
    suspend operator fun invoke(): List<TmdbMovieDto> {
        return repository.getMovieWeekList()
    }
}