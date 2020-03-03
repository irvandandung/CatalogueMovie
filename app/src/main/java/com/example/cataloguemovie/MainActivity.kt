package com.example.cataloguemovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cataloguemovie.Movies.Search.SearchMovieDialog
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_movies, R.id.navigation_tv_show
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }else if (item.itemId == R.id.reminder){
            val intent = Intent(this, SettingReminder::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.searchMovie){
            openMovieDialog()
        }else if (item.itemId == R.id.searchTvShow){
            openTvShowDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openTvShowDialog() {
        val titlemovie = getString(R.string.search_tv_show)
        val newFragment = SearchMovieDialog(titlemovie)
        newFragment.show(supportFragmentManager, "SearchMovie")
    }


    private fun openMovieDialog(){
        val titlemovie = getString(R.string.search_movie)
        val newFragment = SearchMovieDialog(titlemovie)
        newFragment.show(supportFragmentManager, "SearchMovie")
    }
}
