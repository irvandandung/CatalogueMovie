package com.example.cataloguemovie.TvShows.Search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cataloguemovie.Movies.Search.SearchMovie
import com.example.cataloguemovie.R
import com.example.cataloguemovie.TvShows.Adapter.ListTvShowAdapter
import com.example.cataloguemovie.TvShows.Adapter.OnItemClickCallback
import com.example.cataloguemovie.TvShows.Data.TvShow
import com.example.cataloguemovie.TvShows.Detail.DetailTvShow
import com.example.cataloguemovie.TvShows.Viewmodel.ViewModelTvSearch
import com.example.cataloguemovie.TvShows.Viewmodel.Viewmodeltvsearchfactory
import kotlinx.android.synthetic.main.activity_search_tv_show.*

class SearchTvShow : AppCompatActivity() {
    private lateinit var viewModelSearch : ViewModelTvSearch
    private lateinit var listTvAdapter: ListTvShowAdapter
    private lateinit var code_lang: String
    private var list = ArrayList<TvShow>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_tv_show)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showLoading(false)
        code_lang = getString(R.string.codelang)
        val query = intent.getStringExtra(SearchMovie.SEARCH_MOVIE)
        supportActionBar?.title = query
        getDatawithquery(code_lang, query)
    }

    private fun getDatawithquery(codeLang: String, query: String) {
        showLoading(true)
        viewModelSearch = ViewModelProviders.of(this, Viewmodeltvsearchfactory(query, codeLang)).get(ViewModelTvSearch::class.java)
        viewModelSearch.searchtv.observe(this, Observer { tv_show ->
            if (tv_show != null) {
                showLoading(false)
                list = tv_show.tv as ArrayList<TvShow>
                showRecyclerList()
            } else {
                Toast.makeText(this, "Data tidak ada atau internet mati", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            tv_View_search.visibility = View.VISIBLE
        } else {
            tv_View_search.visibility = View.GONE
        }
    }

    private fun showRecyclerList() {
        rv_tv_show_search.layoutManager = LinearLayoutManager(this)
        listTvAdapter = ListTvShowAdapter(list)
        rv_tv_show_search.adapter = listTvAdapter

        listTvAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(tvShow: TvShow) {
                val intent = Intent(this@SearchTvShow, DetailTvShow::class.java)
                intent.putExtra(DetailTvShow.EXTRA_TV_SHOW, tvShow)
                startActivity(intent)
                showSelectedHero(tvShow)
            }
        })
    }

    private fun showSelectedHero(tvShow: TvShow) {
        Toast.makeText(this, "${R.string.open} ${tvShow.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
