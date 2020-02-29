package com.example.cataloguemovie.Favorite.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.Movies.Db.MovieEntity
import com.example.cataloguemovie.R
import kotlinx.android.synthetic.main.cardview_movie.view.*

class AdapterFavoriteMovies(private val listmovies: List<MovieEntity>) :
    RecyclerView.Adapter<AdapterFavoriteMovies.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_movie, parent, false)

        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listmovies.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listmovies[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movies: MovieEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(BuildConfig.URL_POSTER + movies.backdropPath)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)

                tv_item_title.text = movies.originalTitle
                tv_item_dateReleased.text = movies.releaseDate
                tv_item_userScore.text = movies.voteAverage.toString()

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movies) }
            }
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }
}