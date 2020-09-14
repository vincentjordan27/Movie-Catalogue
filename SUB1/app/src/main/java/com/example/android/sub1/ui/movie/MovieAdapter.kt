package com.example.android.sub1.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.sub1.BuildConfig
import com.example.android.sub1.R
import com.example.android.sub1.data.source.local.entity.MovieModel
import kotlinx.android.synthetic.main.list_item.view.*

class MovieAdapter(private val listener: (MovieModel) -> Unit) : PagedListAdapter<MovieModel, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>(){
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movie: MovieModel, listener: (MovieModel) -> Unit){
            with(itemView){
                item_lang.text = movie.lang
                Glide.with(this).load(BuildConfig.IMAGE + movie.image).into(image_item)
                item_title.text = movie.title
                setOnClickListener {
                    listener(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MovieViewHolder(
            view
        )
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
        holder.bind(movie, listener)
    }

}