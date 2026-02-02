package com.dapaeng12.ruachmovie.features.movieList.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapaeng12.ruachmovie.features.movieList.data.datasources.RetrofitManager
import com.dapaeng12.ruachmovie.features.movieList.domian.entities.TmdbMovie
import com.dapaeng12.ruachmovie.features.movieList.domian.repositories.MovieListRepository
import com.dapaeng12.ruachmovie.features.movieList.domian.usecases.GetTrendingDayMoviesUseCase
import com.dapaeng12.ruachmovie.features.movieList.domian.usecases.GetTrendingWeekMoviesUseCase
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieListRepository

) : ViewModel() {
    private val DAY : String = "day"
    private val WEEK : String = "week"

    val TAG : String = "로그"
    var listType : String = ""
    private val _movieList = MutableLiveData<List<TmdbMovie>>()   // 내부에서만 수정
    val movieList: LiveData<List<TmdbMovie>> = _movieList

    var dayMovieList: List<TmdbMovie> = listOf()
    var weekMovieList: List<TmdbMovie> = listOf()

    private val getTrendingDayMoviesUseCase = GetTrendingDayMoviesUseCase(repository)
    private val getTrendingWeekMoviesUseCase = GetTrendingWeekMoviesUseCase(repository)
    init {
        loadMovieList()
    }

    fun loadDay() {
        viewModelScope.launch {
            val response = getTrendingDayMoviesUseCase()
            _movieList.value = response
            listType = DAY
        }
    }

    fun loadWeek() {
        viewModelScope.launch {
            val response = getTrendingWeekMoviesUseCase()
            _movieList.value = response
            listType = WEEK
        }
    }

    fun loadMovieList() {
        viewModelScope.launch {
            val dayResponse = getTrendingDayMoviesUseCase()
            dayMovieList = dayResponse

            val weekResponse = getTrendingWeekMoviesUseCase()
            weekMovieList = weekResponse

            _movieList.value = dayMovieList
            listType = DAY
        }
    }

    fun selectDay() {
        _movieList.value = dayMovieList
        listType = DAY
    }

    fun selectWeek() {
        _movieList.value = weekMovieList
        listType = WEEK
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
