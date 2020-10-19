package com.example.tvshowtracker.listeners

import com.example.tvshowtracker.models.TvShow

interface TvShowListner {
    fun onTvShowClicked(tvShow: TvShow)
}