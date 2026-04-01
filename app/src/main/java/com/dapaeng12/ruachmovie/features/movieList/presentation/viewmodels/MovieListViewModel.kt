package com.dapaeng12.ruachmovie.features.movieList.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieDto
import com.dapaeng12.ruachmovie.features.movieList.data.mappers.toDomain
import com.dapaeng12.ruachmovie.features.movieList.domian.entities.Movie
import com.dapaeng12.ruachmovie.features.movieList.domian.repositories.MovieListRepository
import com.dapaeng12.ruachmovie.features.movieList.domian.usecases.GetTrendingDayMoviesUseCase
import com.dapaeng12.ruachmovie.features.movieList.domian.usecases.GetTrendingWeekMoviesUseCase
import com.dapaeng12.ruachmovie.utils.MOVIE_LIST_TYPE
import kotlinx.coroutines.launch
import kotlin.collections.map

class MovieListViewModel(
    private val repository: MovieListRepository

) : ViewModel() {
    private val DAY : String = "day"
    private val WEEK : String = "week"

    val TAG : String = "로그"
    var listType : String = ""
    var movieListType : MOVIE_LIST_TYPE = MOVIE_LIST_TYPE.DAY
    private val _movieList = MutableLiveData<List<Movie>>()   // 내부에서만 수정
    val movieList: LiveData<List<Movie>> = _movieList

//    var movieListFlow : MutableStateFlow<List<TmdbMovie>> = MutableStateFlow(listOf())

    var dayMovieList: List<Movie> = listOf()
    var weekMovieList: List<Movie> = listOf()

    private val getTrendingDayMoviesUseCase = GetTrendingDayMoviesUseCase(repository)
    private val getTrendingWeekMoviesUseCase = GetTrendingWeekMoviesUseCase(repository)
    init {
        loadMovieList()
    }

    fun loadDay() {
        viewModelScope.launch {
            val response = getTrendingDayMoviesUseCase()
            _movieList.value = response.map { it -> it.toDomain() }
            listType = DAY
            movieListType = MOVIE_LIST_TYPE.DAY
        }
    }

    fun loadWeek() {
        viewModelScope.launch {
            val response = getTrendingWeekMoviesUseCase()
            _movieList.value = response.map { it -> it.toDomain() }
            listType = WEEK
            movieListType = MOVIE_LIST_TYPE.WEEK
        }
    }

    fun loadMovieList() {
        viewModelScope.launch {
            val dayResponse = getTrendingDayMoviesUseCase()
            dayMovieList = dayResponse.map { it -> it.toDomain() }

            val weekResponse = getTrendingWeekMoviesUseCase()
            weekMovieList = weekResponse.map { it -> it.toDomain() }

            _movieList.value = dayMovieList
//            movieListFlow.value = dayMovieList
            listType = DAY
            movieListType = MOVIE_LIST_TYPE.DAY
        }
    }

    fun selectDay() {
        _movieList.value = dayMovieList
//        movieListFlow.value = dayMovieList
        listType = DAY
        movieListType = MOVIE_LIST_TYPE.DAY
    }

    fun selectWeek() {
        _movieList.value = weekMovieList
//        movieListFlow.value = weekMovieList
        listType = WEEK
        movieListType = MOVIE_LIST_TYPE.WEEK
    }
}

class MovieListViewModelFactory(
    private val repository: MovieListRepository
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
