package com.dapaeng12.ruachmovie.features.movieList.data.dtos

import com.google.gson.annotations.SerializedName

data class TmdbMovieListResponseDto(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<TmdbMovieDto>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)

data class TmdbMovieDto(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String?,   // null일 수 있음

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,     // null일 수 있음

    @SerializedName("media_type")
    val mediaType: String?,      // trending 응답에서 종종 포함 (movie 등)

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("release_date")
    val releaseDate: String?,    // 비어있거나 null일 수 있음

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)
