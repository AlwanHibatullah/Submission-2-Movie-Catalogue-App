package com.alwandroid.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alwandroid.moviecatalogue.R
import com.alwandroid.moviecatalogue.model.Movie
import com.alwandroid.moviecatalogue.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_grid.view.*

class MovieAdapter(private val listMovie: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                tv_name.text = movie.title
                tv_ratings.text = movie.rating
                Glide.with(itemView.context)
                    .load(movie.poster!!)
                    .into(img_poster)

                itemView.setOnClickListener {
                    val detailMovieIntent = Intent(itemView.context, DetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(DetailActivity.EXTRA_MOVIE, movie)
                    bundle.putInt(DetailActivity.EXTRA_SEPARATOR, 1)
                    detailMovieIntent.putExtras(bundle)
                    itemView.context.startActivity(detailMovieIntent)
                }
            }
        }
    }
}