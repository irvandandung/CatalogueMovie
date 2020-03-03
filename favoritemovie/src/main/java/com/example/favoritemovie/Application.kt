package com.example.favoritemovie


import android.app.Application
import android.content.ContentResolver
import android.content.Context
import com.example.favoritemovie.ViewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import timber.log.Timber

class Application : Application(){
    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }

    private val contentResolver = module {
        single { instanceContentResolver(get()) }
    }

    private fun instanceContentResolver(context: Context): ContentResolver
            = context.contentResolver

    override fun onCreate() {
        super.onCreate()

        //Timber Debug
        Timber.plant(Timber.DebugTree())

        //Create Koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(listOf(contentResolver, viewModelModule))
        }
    }


}