package com.example.cataloguemovie.Favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cataloguemovie.Favorite.Movies.FavoriteMovieFragment
import com.example.cataloguemovie.Favorite.TvShows.FavoriteTvShowFragment

class AdapterViewPager(fm: FragmentManager, val fav1: String, val fav2: String) : FragmentPagerAdapter(fm) {
    private val pages = listOf(
        FavoriteMovieFragment(),
        FavoriteTvShowFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): String {
        return when (position) {
            0 -> fav1
            else -> fav2
        }
    }
}