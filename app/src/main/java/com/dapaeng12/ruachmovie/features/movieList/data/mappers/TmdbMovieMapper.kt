package com.dapaeng12.ruachmovie.features.movieList.data.mappers

import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieDto
import com.dapaeng12.ruachmovie.features.movieList.domian.entities.Movie
import com.dapaeng12.ruachmovie.utils.makeImageUrl
import com.dapaeng12.ruachmovie.utils.tmdbMoviePostBaseUrl

fun TmdbMovieDto.toDomain() : Movie =
    Movie(
        id = id,
        title = title,
        releaseDate = releaseDate,
        posterUrl = posterPath?.makeImageUrl(tmdbMoviePostBaseUrl)
    )