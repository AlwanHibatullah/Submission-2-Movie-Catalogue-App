package com.alwandroid.moviecatalogue.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alwandroid.moviecatalogue.R
import com.alwandroid.moviecatalogue.model.Movie
import com.alwandroid.moviecatalogue.model.TvShow
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
        const val EXTRA_SEPARATOR = "separator"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvTitle: TextView = findViewById(R.id.tv_title)
        val tvDate: TextView = findViewById(R.id.tv_date)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val imgPoster: ImageView = findViewById(R.id.img_poster)

        var titleBar: String? = ""
        val separator = intent.getIntExtra(EXTRA_SEPARATOR, 0)

        if (separator == 1) {
            val movie = intent.getParcelableExtra(EXTRA_MOVIE) as Movie

            tvTitle.text = movie.title.toString()
            tvDate.text = movie.date.toString()
            tvDescription.text = movie.description.toString()
            Glide.with(this)
                .load(movie.poster)
                .into(imgPoster)

            titleBar = movie.title
        } else {
            val tvshow = intent.getParcelableExtra(EXTRA_TVSHOW) as TvShow

            tvTitle.text = tvshow.title.toString()
            tvDate.text = tvshow.date.toString()
            tvDescription.text = tvshow.description.toString()
            Glide.with(this)
                .load(tvshow.poster)
                .into(imgPoster)

            titleBar = tvshow.title
        }

        supportActionBar?.title = titleBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
