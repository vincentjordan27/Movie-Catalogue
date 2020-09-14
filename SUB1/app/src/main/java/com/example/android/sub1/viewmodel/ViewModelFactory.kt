package com.example.android.sub1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.sub1.data.CatalogueRepository
import com.example.android.sub1.di.Injection
import com.example.android.sub1.ui.detail.DetailViewModel
import com.example.android.sub1.ui.favorite.FavoriteViewModel
import com.example.android.sub1.ui.movie.MovieViewModel
import com.example.android.sub1.ui.tvShow.TvShowViewModel

class ViewModelFactory private constructor(private val catalogueRepository: CatalogueRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(catalogueRepository) as T
            }
            else -> throw Throwable("Unkonown ViewModel Class: " + modelClass.name)
        }
    }
}