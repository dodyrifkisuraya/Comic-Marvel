package com.dorizu.marvel.favorit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dorizu.marvel.R
import com.dorizu.marvel.core.ui.ComicsAdapter
import com.dorizu.marvel.core.ui.ViewModelFactory
import com.dorizu.marvel.detail.DetailComicActivity
import kotlinx.android.synthetic.main.fragment_favorit.*

class FavoritFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val comicsAdapter = ComicsAdapter()
            comicsAdapter.onItemClick = {
                val intent = Intent(activity, DetailComicActivity::class.java)
                intent.putExtra(DetailComicActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteComic.observe(viewLifecycleOwner, Observer {
                comicsAdapter.setData(it)
                view_empty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(rv_favorite){
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = comicsAdapter
            }
        }
    }
}