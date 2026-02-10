package com.dapaeng12.ruachmovie.utils

object Constants{
    const val TAG: String = "로그"
}

enum class SEARCH_TYPE{
    PHOTO,
    USER
}

enum class RESPONSE_STATEUS {
    SUCCESS,
    FAIL,
    NO_CONTENT
}

object SplashAPI{
    const val BASE_URL: String = "https://api.unsplash.com/"

    const val CLIENT_ID : String = "bEgGCHuX35foFh6uBlsK4SkHrPL31U1_vqYHt-JZGUY"

    const val SEARCH_PHOTOS : String = "search/photos"

    const val SEARCH_USERS : String = "search/users"
}