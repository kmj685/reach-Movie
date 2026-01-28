package com.dapaeng12.ruachmovie.features.movieList.presentation.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dapaeng12.ruachmovie.R
import com.dapaeng12.ruachmovie.features.movieList.data.repositories.MovieListRepositoryImpl
import com.dapaeng12.ruachmovie.features.movieList.domian.usecases.GetTrendingDayMoviesUseCase
import com.dapaeng12.ruachmovie.features.movieList.presentation.activities.recyclerviews.MovieRecycleAdapter
import com.dapaeng12.ruachmovie.features.movieList.presentation.viewmodels.MovieListViewModel
import com.dapaeng12.ruachmovie.features.movieList.presentation.viewmodels.MovieListViewModelFactory
import kotlin.getValue

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieRecycleAdapter
    private val viewModel : MovieListViewModel by viewModels{
        MovieListViewModelFactory(GetTrendingDayMoviesUseCase(MovieListRepositoryImpl()))
    }

    private lateinit var movieListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.movieAdapter = MovieRecycleAdapter()
        setContentView(R.layout.activity_main)

        movieListRecyclerView = findViewById(R.id.movie_list_recycler_view)
        viewModel.movieList.observe(this) { list ->
            movieAdapter.submitList(list)
        }

        movieListRecyclerView.apply {
            //
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}