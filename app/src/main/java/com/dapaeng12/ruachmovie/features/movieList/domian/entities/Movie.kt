package com.dapaeng12.ruachmovie.features.movieList.domian.entities

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: String?,
    val posterUrl: String?   // ✅ 완성 URL
)
