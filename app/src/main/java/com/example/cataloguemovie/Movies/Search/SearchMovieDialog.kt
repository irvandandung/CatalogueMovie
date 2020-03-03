package com.example.cataloguemovie.Movies.Search

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.cataloguemovie.R
import com.example.cataloguemovie.TvShows.Search.SearchTvShow

class SearchMovieDialog(title : String) : DialogFragment(){
    private val titlee : String = title
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            val views = inflater.inflate(R.layout.searchdialog,null)
            val textTitle = views?.findViewById<TextView>(R.id.textTitle)
            textTitle?.text = titlee
            builder.setView(views)
                // Add action buttons
                .setPositiveButton(R.string.search,
                    DialogInterface.OnClickListener { dialog, id ->
                        val et_query = views?.findViewById<EditText>(R.id.edit_query)
                        val query : String = et_query?.text.toString()
                        var intent : Intent
                        if (titlee == getString(R.string.search_movie)){
                            intent = Intent(context, SearchMovie::class.java)
                        }else{
                            intent = Intent(context, SearchTvShow::class.java)
                        }
                        intent.putExtra(SearchMovie.SEARCH_MOVIE, query)
                        startActivity(intent)
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}