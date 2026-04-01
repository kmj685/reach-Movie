package com.dapaeng12.ruachmovie.utils

import com.dapaeng12.ruachmovie.features.movieList.domian.entities.Movie

fun String.makeImageUrl(baseUrl : String) : String {
    return "${baseUrl}$this"
}

