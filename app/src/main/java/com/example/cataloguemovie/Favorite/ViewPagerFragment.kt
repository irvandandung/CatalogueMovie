package com.example.cataloguemovie.Favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.cataloguemovie.R
import com.google.android.material.tabs.TabLayout

class ViewPagerFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_view_pager, container, false)
        viewPager = view.findViewById(R.id.viewpager_main)
        tabs = view.findViewById(R.id.tabs_main)
        val fav1 = getString(R.string.favorite) + " " + getString(R.string.movies)
        val fav2 = getString(R.string.favorite) + " " + getString(R.string.tv_show)
        val fragmentAdapter = AdapterViewPager(childFragmentManager, fav1, fav2)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
        return view
    }
}
