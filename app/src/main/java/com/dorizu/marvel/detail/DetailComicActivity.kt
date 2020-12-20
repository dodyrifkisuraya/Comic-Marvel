package com.dorizu.marvel.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dorizu.marvel.R
import com.dorizu.marvel.core.domain.model.Comic
import com.dorizu.marvel.core.ui.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_comic.*
import kotlinx.android.synthetic.main.content_detail_comic.*

class DetailComicActivity : AppCompatActivity() {
    private lateinit var detailComicViewModel: DetailComicViewModel

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_comic)
        setSupportActionBar(toolbar)

        val factory = ViewModelFactory.getInstance(this)
        detailComicViewModel = ViewModelProvider(this, factory)[DetailComicViewModel::class.java]

        val detailComic = intent.getParcelableExtra<Comic>(EXTRA_DATA)
        showDetailComic(detailComic)
    }

    private fun showDetailComic(detailComic: Comic?){
        detailComic?.let {
            supportActionBar?.title = detailComic.title
            tv_detail_description.text = detailComic.description
            Glide.with(this)
                .load(detailComic.thumbnail)
                .into(img_detail_image)

            var statusFavorite = detailComic.isFavorite
            setStatusFavorite(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailComicViewModel.setFavoriteComic(detailComic, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24_red))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }
}