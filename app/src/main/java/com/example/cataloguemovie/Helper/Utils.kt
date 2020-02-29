package com.example.cataloguemovie.Helper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val calendar = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    return dateFormat.format(calendar)
}