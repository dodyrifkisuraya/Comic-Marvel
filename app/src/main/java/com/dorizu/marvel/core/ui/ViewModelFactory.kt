package com.dorizu.marvel.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dorizu.marvel.core.di.Injection
import com.dorizu.marvel.core.domain.usecase.ComicsUseCase
import com.dorizu.marvel.detail.DetailComicViewModel
import com.dorizu.marvel.favorit.FavoriteViewModel
import com.dorizu.marvel.home.HomeViewModel

class ViewModelFactory private constructor(private val comicsUseCase: ComicsUseCase):
    ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: ViewModelFactory(
                Injection.provideComicUseCase(context)
            )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(comicsUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(comicsUseCase) as T
            }
            modelClass.isAssignableFrom(DetailComicViewModel::class.java) -> {
                DetailComicViewModel(comicsUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}