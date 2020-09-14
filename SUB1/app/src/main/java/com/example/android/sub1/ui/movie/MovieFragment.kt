package com.example.android.sub1.ui.movie

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
import com.example.android.sub1.viewmodel.ViewModelFactory
import com.example.android.sub1.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("movie_id", it.id)
                Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            viewModel.getMovieData().observe(viewLifecycleOwner, Observer { movies ->
                if (movies != null){
                    when(movies.status){
                        Status.LOADING -> prog_movie.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            prog_movie.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            prog_movie.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            })

            with(rv_movie_frag){
               layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}
