package com.example.android.sub1.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.android.sub1.BuildConfig
import com.example.android.sub1.ui.MainActivity
import com.example.android.sub1.R
import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel
import com.example.android.sub1.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val factory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]

        setSupportActionBar(detail_toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(getString(R.string.back_main))


        if (intent.getIntExtra("movie_id", 0) != 0){
            prog_detail.visibility = View.VISIBLE
            viewModel.getMovieData(intent.getIntExtra("movie_id",0)).observe(this , Observer {
                loadDataMovie(it)
                prog_detail.visibility = View.GONE
            })



        }
        else {
            prog_detail.visibility = View.VISIBLE
            viewModel.getTvShowData(intent.getIntExtra("tv_show_id",0)).observe(this, Observer {
                prog_detail.visibility = View.GONE
                loadDataTvShow(it)
            })
        }

        swp.setOnRefreshListener {
            if (intent.getIntExtra("movie_id", 0) != 0){
                prog_detail.visibility = View.VISIBLE
                viewModel.getMovieData(intent.getIntExtra("movie_id",0)).observe(this , Observer {
                    prog_detail.visibility = View.GONE
                    loadDataMovie(it)
                })


            }
            else {
                prog_detail.visibility = View.VISIBLE
                viewModel.getTvShowData(intent.getIntExtra("tv_show_id",0)).observe(this, Observer {
                    prog_detail.visibility = View.GONE
                    loadDataTvShow(it)
                })
            }
        }

    }

    private fun loadDataTvShow(tvShowDetail: TvShowModel?) {
        Glide.with(this).load(BuildConfig.IMAGE + tvShowDetail?.image).into(img_detail_blur)
        Glide.with(this).load(BuildConfig.IMAGE + tvShowDetail?.image).into(img_detail)
        detail_title.text = tvShowDetail?.title
        detail_release.text = tvShowDetail?.release
        detail_vote.text = tvShowDetail?.vote.toString()
        detail_overview.text = tvShowDetail?.overview
        tvShowDetail?.favorite?.let { setState(it) }
        btn_fav.setOnClickListener {
            if (tvShowDetail?.favorite == true){
                val state = !tvShowDetail.favorite
                viewModel.setFavoriteTvShow(tvShowDetail, state)
                setState(state)


            } else if (tvShowDetail?.favorite == false){
                val state = !tvShowDetail.favorite
                viewModel.setFavoriteTvShow(tvShowDetail, state)
                setState(state)
            }
        }

    }

    private fun loadDataMovie(movieDetail: MovieModel?) {
        Glide.with(this).load(BuildConfig.IMAGE + movieDetail?.image).into(img_detail_blur)
        Glide.with(this).load(BuildConfig.IMAGE + movieDetail?.image).into(img_detail)
        detail_title.text = movieDetail?.title
        detail_release.text = movieDetail?.release
        detail_vote.text = movieDetail?.vote.toString()
        detail_overview.text = movieDetail?.overview
        movieDetail?.favorite?.let { setState(it) }
        btn_fav.setOnClickListener {
            if (movieDetail?.favorite == true){
                val state = !movieDetail.favorite
                viewModel.setFavoriteMovie(movieDetail, state)
                setState(state)
            }else if (movieDetail?.favorite == false) {
                val state = !movieDetail.favorite
                viewModel.setFavoriteMovie(movieDetail, state)
                setState(state)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                startActivity(Intent(this,
                    MainActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setState(state: Boolean){
        if (state){
            btn_fav.setBackgroundResource(R.drawable.ic_favorite)
        } else {
            btn_fav.setBackgroundResource(R.drawable.ic_not_favorite)
        }
    }
}
