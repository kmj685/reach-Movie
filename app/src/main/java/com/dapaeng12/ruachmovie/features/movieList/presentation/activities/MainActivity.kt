package com.dapaeng12.ruachmovie.features.movieList.presentation.activities

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.TextViewCompat
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
        MovieListViewModelFactory(MovieListRepositoryImpl())
    }

    private lateinit var movieListRecyclerView: RecyclerView
    private lateinit var dayButton : Button
    private lateinit var weekButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.movieAdapter = MovieRecycleAdapter()
        setContentView(R.layout.activity_main)

        movieListRecyclerView = findViewById(R.id.movie_list_recycler_view)
        dayButton = findViewById(R.id.day_button)
        weekButton = findViewById(R.id.week_button)

        dayButton.setOnClickListener { clickDayBtn() }
        weekButton.setOnClickListener { clickWeekBtn() }
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
    fun clickDayBtn() {
        if (viewModel.listType == "day") {
            return
        } else {
            viewModel.selectDay()
            selectBtn(dayButton)
            unSelectBtn(weekButton)
        }
    }

    fun clickWeekBtn() {
        if (viewModel.listType == "week") {
            return
        } else {
            viewModel.selectWeek()
            selectBtn(weekButton)
            unSelectBtn(dayButton)
        }
    }

    fun selectBtn(button: Button) {
        val btn = button
        btn.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(this, R.color.selectBtnBg)
        )
        TextViewCompat.setTextAppearance(btn, R.style.BtnText_Bold)
        btn.setTextColor(ContextCompat.getColor(this, android.R.color.white))
    }

    fun unSelectBtn(button: Button) {
        val btn = button
        btn.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(this, R.color.unSelectBtnBg)
        )
        TextViewCompat.setTextAppearance(btn, R.style.BtnText_Normal)
        btn.setTextColor(ContextCompat.getColor(this, android.R.color.black))
    }

}