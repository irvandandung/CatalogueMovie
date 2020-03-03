package com.example.favoritemovie


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritemovie.Adapter.FavoriteMovieAdapter
import com.example.favoritemovie.Base.BaseActivity
import com.example.favoritemovie.Model.FavoriteMovie
import com.example.favoritemovie.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: FavoriteMovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FavoriteMovieAdapter(this::clickAdapter)


        main_rv.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        main_rv.adapter = adapter

        viewModel.favoriteMovie.observe(this, Observer {
            adapter.updateList(it)
        })

    }
    private fun clickAdapter(item:FavoriteMovie) {

    }
}
