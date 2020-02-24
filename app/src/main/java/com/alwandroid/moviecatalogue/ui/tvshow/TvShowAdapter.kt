package com.alwandroid.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alwandroid.moviecatalogue.R
import com.alwandroid.moviecatalogue.model.TvShow
import com.alwandroid.moviecatalogue.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_grid.view.*

class TvShowAdapter(private val listTvShow: ArrayList<TvShow>) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow) {
            with(itemView) {
                tv_name.text = tvShow.title
                tv_ratings.text = tvShow.rating
                Glide.with(itemView.context)
                    .load(tvShow.poster!!)
                    .into(img_poster)

                itemView.setOnClickListener {
                    val detailTvShowIntent = Intent(itemView.context, DetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(DetailActivity.EXTRA_TVSHOW, tvShow)
                    bundle.putInt(DetailActivity.EXTRA_SEPARATOR, 2)
                    detailTvShowIntent.putExtras(bundle)
                    itemView.context.startActivity(detailTvShowIntent)
                }
            }
        }
    }
}