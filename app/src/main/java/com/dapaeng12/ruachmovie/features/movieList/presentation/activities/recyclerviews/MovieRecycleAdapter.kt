package com.dapaeng12.ruachmovie.features.movieList.presentation.activities.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapaeng12.ruachmovie.R
import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieDto
import com.dapaeng12.ruachmovie.features.movieList.domian.entities.Movie

class MovieRecycleAdapter: RecyclerView.Adapter<MovieViewHolder>() {
    val TAG : String = "로그"

    private var movieList : List<Movie> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_recycler_item, parent, false))
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.bind(this.movieList[position], position)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun submitList(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}