package com.example.tvshowtracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.DataBinderMapperImpl
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowtracker.R
import com.example.tvshowtracker.databinding.TvShowItemBinding
import com.example.tvshowtracker.models.TvShow

class TVShowsAdapter(): RecyclerView.Adapter<TVShowsAdapter.TvShowViewHolder>() {

    private lateinit var tvShows:List<TvShow>

    private var layoutInflater:LayoutInflater? = null

    constructor(tvShows: List<TvShow>) : this() {
        this.tvShows = tvShows
    }


    inner class TvShowViewHolder : RecyclerView.ViewHolder {

        //As the layout name is tv_show_item.xml, the generated binding class will be TvShowItemBinding.
        //In onCreateViewHolder() method, tv_show_item layout is inflated with the help of TvShowItemBinding class.
        var tvShowItemBinding :TvShowItemBinding

        constructor(a:TvShowItemBinding):super(a.root) {
            this.tvShowItemBinding = a
        }

        fun bindTvShow(tvShow:TvShow){

            tvShowItemBinding.tvShow = tvShow
            tvShowItemBinding.executePendingBindings()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
       if (layoutInflater == null){
           layoutInflater = LayoutInflater.from(parent.context)
       }

       var tvShowItemBinding: TvShowItemBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.tv_show_item,parent,false)

        return TvShowViewHolder(tvShowItemBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bindTvShow(tvShows.get(position))
    }

    override fun getItemCount(): Int {
        return  tvShows.size
    }
}