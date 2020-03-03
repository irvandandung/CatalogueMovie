package com.example.favoritemovie.Adapter

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.favoritemovie.Base.BaseRecyclerViewAdapter
import com.example.favoritemovie.Model.FavoriteMovie
import com.example.favoritemovie.R
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoriteMovieAdapter(private val listener : (item: FavoriteMovie) -> Unit):BaseRecyclerViewAdapter<FavoriteMovie, FavoriteMovieAdapter.ViewHolder>(){

    override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(listener, view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(get(position))
    }

    class ViewHolder( private val listener: (item: FavoriteMovie) -> Unit
    ,view : View) : RecyclerView.ViewHolder(view){
        fun bind(item: FavoriteMovie) {
            val uri = Uri.parse("https://image.tmdb.org/t/p/w92/${item.poster}")
            Glide.with(itemView.context)
                .load(uri)
                .into(itemView.i_movie_poster)

            //! Set other stuff
            itemView.i_movie_title.text = item.title
            itemView.i_movie_title.setTextColor(Color.BLACK)
            itemView.i_movie_overview.text = item.overview
            itemView.i_movie_overview.setTextColor(Color.BLACK)

            itemView.setOnClickListener { listener.invoke(item) }

        }

    }
}