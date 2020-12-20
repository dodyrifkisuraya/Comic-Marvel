package com.dorizu.marvel.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dorizu.marvel.R
import com.dorizu.marvel.core.data.Resource
import com.dorizu.marvel.core.ui.ComicsAdapter
import com.dorizu.marvel.core.ui.ViewModelFactory
import com.dorizu.marvel.detail.DetailComicActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val comicAdapter = ComicsAdapter()
            comicAdapter.onItemClick = { selectedItem ->
                val intent = Intent(activity, DetailComicActivity::class.java)
                intent.putExtra(DetailComicActivity.EXTRA_DATA, selectedItem)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            Log.e("DATA", activity.toString())
            homeViewModel.comic.observe(viewLifecycleOwner, Observer { comic ->
                Log.e("DATA", comic.data.toString())
                if (comic != null) {
                    when (comic) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            comicAdapter.setData(comic.data)
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            tv_error.text = comic.message ?: "Upps..Ada kesalahan!"
                        }
                    }

                }
            })

            with(rv_comic) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = comicAdapter
            }
        }
    }
}