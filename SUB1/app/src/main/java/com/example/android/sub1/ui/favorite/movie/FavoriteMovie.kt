package com.example.android.sub1.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.android.sub1.R
import com.example.android.sub1.ui.detail.DetailActivity
import com.example.android.sub1.ui.favorite.FavoriteViewModel
import com.example.android.sub1.ui.movie.MovieAdapter
import com.example.android.sub1.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovie : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            val movieAdapter = MovieAdapter{
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("movie_id", it.id)
                Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }

            prog_fav_movie.visibility = View.VISIBLE
            viewModel.getFavMovie().observe(viewLifecycleOwner, Observer {movies ->
                if (movies != null){
                    movieAdapter.submitList(movies)
                    movieAdapter.notifyDataSetChanged()
                    prog_fav_movie.visibility = View.GONE
                }
            })

            with(rv_fav_movie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            swp_fav_movie.setOnRefreshListener {
                viewModel.getFavMovie().observe(viewLifecycleOwner, Observer {movies ->
                    if (movies != null){
                        movieAdapter.submitList(movies)
                        movieAdapter.notifyDataSetChanged()
                        prog_fav_movie.visibility = View.GONE
                    }
                })
                swp_fav_movie.isRefreshing = false
            }
        }
    }
}
