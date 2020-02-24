package com.alwandroid.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alwandroid.moviecatalogue.model.Movie
import com.alwandroid.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private val movieList = ArrayList<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieList.addAll(getListMovies())
        val listMovieAdapter = MovieAdapter(movieList)
        rv_movies.layoutManager = GridLayoutManager(view.context, 2)
        rv_movies.adapter = listMovieAdapter
        rv_movies.setHasFixedSize(true)
    }

    private fun getListMovies(): ArrayList<Movie>{
        val dataTitle = resources.getStringArray(R.array.data_movie_title)
        val dataDate = resources.getStringArray(R.array.data_movie_date)
        val dataDescription = resources.getStringArray(R.array.data_movie_description)
        val dataRatings = resources.getStringArray(R.array.data_movie_rating)
        val dataPhoto = resources.obtainTypedArray(R.array.data_poster)

        val listMovie = ArrayList<Movie>()
        for (position in dataTitle.indices){
            val movie = Movie(
                dataPhoto.getResourceId(position, -1),
                dataTitle[position],
                dataDescription[position],
                dataRatings[position],
                dataDate[position]
            )
            listMovie.add(movie)
        }
        dataPhoto.recycle()

        return listMovie
    }
}