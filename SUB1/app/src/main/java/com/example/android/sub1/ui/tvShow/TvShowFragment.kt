package com.example.android.sub1.ui.tvShow

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
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvAdapter = TvShowAdapter {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("tv_show_id", it.id)
                startActivity(intent)
            }
            prog_tv_show.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner , Observer {tvShow ->
               if (tvShow != null){
                   when(tvShow.status){
                       Status.LOADING -> prog_tv_show.visibility = View.VISIBLE
                       Status.SUCCESS -> {
                           prog_tv_show.visibility = View.GONE
                           tvAdapter.submitList(tvShow.data)
                           tvAdapter.notifyDataSetChanged()
                       }
                       Status.ERROR -> {
                           prog_tv_show.visibility = View.GONE
                           Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()

                       }
                   }
               }
            })
            with(rv_tv_show_frag){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }
}
