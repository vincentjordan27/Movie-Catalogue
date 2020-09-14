package com.example.android.sub1.ui.favorite.tvShow

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
import com.example.android.sub1.ui.tvShow.TvShowAdapter
import com.example.android.sub1.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShow : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null){
            val factory  = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            val tvShowAdapter = TvShowAdapter{
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("tv_show_id", it.id)
                Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }

            prog_fav_tv_show.visibility = View.VISIBLE
            viewModel.getFavTvShow().observe(viewLifecycleOwner, Observer {tvShow ->
                if (tvShow != null){
                    tvShowAdapter.submitList(tvShow)
                    tvShowAdapter.notifyDataSetChanged()
                    prog_fav_tv_show.visibility = View.GONE
                }
            })

            with(rv_fav_tv_show){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

            swp_fav_tv_show.setOnRefreshListener {
                viewModel.getFavTvShow().observe(viewLifecycleOwner, Observer {tvShow ->
                    if (tvShow != null){
                        tvShowAdapter.submitList(tvShow)
                        tvShowAdapter.notifyDataSetChanged()
                        prog_fav_tv_show.visibility = View.GONE
                    }
                })
                swp_fav_tv_show.isRefreshing = false
            }
        }
    }
}
