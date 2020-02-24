package com.alwandroid.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alwandroid.moviecatalogue.ui.movie.MovieFragment
import com.alwandroid.moviecatalogue.ui.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var title: String? = null
    private val position: Int = R.id.navigation_movies

    companion object {
        private const val TITLE: String = "title"
        private const val POSITION: String = "position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.navigation_movies -> {
                        setMovieFragment()
                        return true
                    }
                    R.id.navigation_tvshow -> {
                        setTvShowFragment()
                        return true
                    }
                }
                return false
            }
        })

        if (savedInstanceState != null) {
            val position = savedInstanceState.getInt(POSITION)
            val title = savedInstanceState.getString(TITLE)

            if (position == R.id.navigation_movies) {
                setMovieFragment()
            } else {
                setTvShowFragment()
            }
        } else {
            setMovieFragment()
            supportActionBar?.title = resources.getString(R.string.app_name)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(POSITION, position)
        outState.putString(TITLE, title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.language_menu) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMovieFragment() {
        val movieFragment = MovieFragment()
        val fragment: Fragment? =
            supportFragmentManager.findFragmentByTag(MovieFragment::class.java.simpleName)

        if (fragment !is MovieFragment) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.nav_host_fragment,
                    movieFragment,
                    MovieFragment::class.java.simpleName
                ).commit()
        }
    }

    private fun setTvShowFragment() {
        val tvShowFragment = TvShowFragment()
        val fragment: Fragment? =
            supportFragmentManager.findFragmentByTag(TvShowFragment::class.java.simpleName)

        if (fragment !is TvShowFragment) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.nav_host_fragment,
                    tvShowFragment,
                    TvShowFragment::class.java.simpleName
                ).commit()
        }
    }
}
