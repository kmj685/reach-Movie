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
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getTrendingDayMoviesUseCase: GetTrendingDayMoviesUseCase
) : ViewModel() {
    val TAG : String = "로그"
    private val _movieList = MutableLiveData<List<TmdbMovie>>()   // 내부에서만 수정
    val movieList: LiveData<List<TmdbMovie>> = _movieList

    init {
        loadDay()
    }

    fun loadDay() {
        viewModelScope.launch {
            val response = getTrendingDayMoviesUseCase()
            _movieList.value = response
        }
    }
}

class MovieListViewModelFactory(
    private val getTrendingDayMoviesUseCase: GetTrendingDayMoviesUseCase
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(getTrendingDayMoviesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
