package com.alwandroid.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alwandroid.moviecatalogue.R
import com.alwandroid.moviecatalogue.model.TvShow
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TvShowFragment : Fragment() {

    private val tvShowList = ArrayList<TvShow>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tvshow, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowList.addAll(getListTvShow())
        val listTvShowAdapter = TvShowAdapter(tvShowList)
        rv_tvshow.layoutManager = GridLayoutManager(view.context, 2)
        rv_tvshow.adapter = listTvShowAdapter
        rv_tvshow.setHasFixedSize(true)
    }

    private fun getListTvShow(): ArrayList<TvShow> {
        val dataTitle = resources.getStringArray(R.array.data_tvshow_title)
        val dataDate = resources.getStringArray(R.array.data_tvshow_date)
        val dataDescription = resources.getStringArray(R.array.data_tvshow_description)
        val dataRatings = resources.getStringArray(R.array.data_tvshow_rating)
        val dataPhoto = resources.obtainTypedArray(R.array.data_tvshow_poster)

        val listTvShow = ArrayList<TvShow>()
        for (position in dataTitle.indices) {
            val movie = TvShow(
                dataPhoto.getResourceId(position, -1),
                dataTitle[position],
                dataDescription[position],
                dataRatings[position],
                dataDate[position]
            )
            listTvShow.add(movie)
        }
        dataPhoto.recycle()

        return listTvShow
    }
}