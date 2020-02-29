package com.example.cataloguemovie.TvShows


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cataloguemovie.R
import com.example.cataloguemovie.TvShows.Adapter.ListTvShowAdapter
import com.example.cataloguemovie.TvShows.Adapter.OnItemClickCallback
import com.example.cataloguemovie.TvShows.Data.TvShow
import com.example.cataloguemovie.TvShows.Detail.DetailTvShow
import com.example.cataloguemovie.TvShows.Viewmodel.ViewModelTvSearch
import com.example.cataloguemovie.TvShows.Viewmodel.Viewlisttvshow
import com.example.cataloguemovie.TvShows.Viewmodel.Viewlisttvshowfactory
import com.example.cataloguemovie.TvShows.Viewmodel.Viewmodeltvsearchfactory
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

class FragmentTvShow : Fragment() {
    private var list = ArrayList<TvShow>()
    private lateinit var vieModel: Viewlisttvshow
    private lateinit var viewModelSearch : ViewModelTvSearch
    private lateinit var listTvAdapter: ListTvShowAdapter
    private lateinit var code_lang: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    private fun getAllDataonFragment(codelang:String){
        vieModel = ViewModelProviders.of(this, Viewlisttvshowfactory(1, codelang)).get(Viewlisttvshow::class.java)
        vieModel.tvshow.observe(this, Observer { tv_show ->
            if (tv_show != null) {
                showLoading(false)
                list = tv_show.tv as ArrayList<TvShow>
                showRecyclerList()
            } else {
                Toast.makeText(context, "Data tidak ada atau internet mati", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_tv_show.setHasFixedSize(true)
        showLoading(true)
        code_lang = getString(R.string.codelang)
        view.Bt_search.setOnClickListener {
            showLoading(true)
            getDatawithquery(code_lang, view)
        }
        getAllDataonFragment(code_lang)
    }

    private fun getDatawithquery(codeLang: String, view: View) {
        val query : String = view.Et_search.text.toString()
        viewModelSearch = ViewModelProviders.of(this, Viewmodeltvsearchfactory(query, codeLang)).get(ViewModelTvSearch::class.java)
        viewModelSearch.getSetDetailS(query, codeLang)
        viewModelSearch.getDetailS().observe(this, Observer { tv_show ->
            if (tv_show != null) {
                showLoading(false)
                list = tv_show.tv as ArrayList<TvShow>
                showRecyclerList()
            } else {
                Toast.makeText(context, "Data tidak ada atau internet mati", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showRecyclerList() {
        rv_tv_show?.layoutManager = LinearLayoutManager(context)
        listTvAdapter = ListTvShowAdapter(list)
        rv_tv_show?.adapter = listTvAdapter

        listTvAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(tvShow: TvShow) {
                val intent = Intent(activity, DetailTvShow::class.java)
                intent.putExtra(DetailTvShow.EXTRA_TV_SHOW, tvShow)
                startActivity(intent)
                showSelectedHero(tvShow)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            tv_View.visibility = View.VISIBLE
        } else {
            tv_View.visibility = View.GONE
        }
    }

    private fun showSelectedHero(tvShow: TvShow) {
        Toast.makeText(activity, "${R.string.open} ${tvShow.name}", Toast.LENGTH_SHORT).show()
    }


}
