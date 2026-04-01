package com.dapaeng12.ruachmovie.features.movieList.presentation.activities.recyclerviews

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dapaeng12.ruachmovie.App
import com.dapaeng12.ruachmovie.R
import com.dapaeng12.ruachmovie.features.movieList.data.dtos.TmdbMovieDto
import com.dapaeng12.ruachmovie.features.movieList.domian.entities.Movie
import com.dapaeng12.ruachmovie.utils.makeImageUrl
import com.dapaeng12.ruachmovie.utils.tmdbMoviePostBaseUrl

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val TAG : String = "로그"

    private val movieTitleTextView: TextView = itemView.findViewById(R.id.movie_title_txt)
    private val releaseDateTextView: TextView = itemView.findViewById(R.id.release_date)
    private val indexTextView: TextView = itemView.findViewById(R.id.index)
    private val moviePostImageView: ImageView = itemView.findViewById(R.id.movie_post_img)

    init {
        Log.d(TAG, "MovieViewHolder - init() called")
    }

    fun bind(movie: Movie, index:Int) {
        Log.d(TAG, "MovieViewHolder - bind() called")
        movieTitleTextView.text = movie.title
        releaseDateTextView.text = movie.releaseDate
        indexTextView.text = "${index + 1}"


        // 객체지향 적 사고력 키우기
//        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide
            .with(App.instance)
            .load(movie.posterUrl)
//            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(moviePostImageView)
    }
}