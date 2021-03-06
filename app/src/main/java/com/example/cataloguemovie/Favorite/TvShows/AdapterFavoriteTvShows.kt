package com.example.cataloguemovie.Favorite.TvShows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.R
import com.example.cataloguemovie.TvShows.Adapter.OnItemClickCallback
import com.example.cataloguemovie.TvShows.Data.TvShow
import com.example.cataloguemovie.TvShows.Db.TvShowEntity
import kotlinx.android.synthetic.main.cardview_movie.view.*
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.view.*

class AdapterFavoriteTvShows(private val listtvshow: List<TvShowEntity>) :
    RecyclerView.Adapter<AdapterFavoriteTvShows.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tvshow, parent, false)

        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listtvshow.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listtvshow[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(BuildConfig.URL_POSTER + tvShow.backdropPath)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)

                tv_item_title?.text = tvShow.originalTitle
                tv_item_dateReleased?.text = tvShow.firstAirDate
                tv_item_userScore?.text = tvShow.voteAverage.toString()
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvShow) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShowEntity)
    }
}