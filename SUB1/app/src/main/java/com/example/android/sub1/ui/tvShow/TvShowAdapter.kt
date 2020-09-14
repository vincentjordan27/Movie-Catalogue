package com.example.android.sub1.ui.tvShow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.sub1.BuildConfig
import com.example.android.sub1.R
import com.example.android.sub1.data.source.local.entity.TvShowModel
import kotlinx.android.synthetic.main.list_item.view.*

class TvShowAdapter(private val listener: (TvShowModel) -> Unit) : PagedListAdapter<TvShowModel, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowModel>(){
            override fun areItemsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem == newItem
            }

        }
    }


    class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
       fun bind(tvShowModel: TvShowModel, listener: (TvShowModel) -> Unit){
           with(itemView){
               Glide.with(this).load(BuildConfig.IMAGE + tvShowModel.image).into(image_item)
               item_title.text = tvShowModel.title
               item_lang.text = tvShowModel.lang
               setOnClickListener {
                   listener(tvShowModel)
               }
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return TvShowViewHolder(
            view
        )
    }


    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null)
        holder.bind(tvShow, listener)
    }

}