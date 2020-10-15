package com.example.tvshowtracker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowtracker.repository.MostPopularTvShowRepository
import com.example.tvshowtracker.responses.TvShowsResponse

class MostPopularTvShowsViewModel() : ViewModel() {

    private var mostPopularTvShowRepository: MostPopularTvShowRepository

    init {
        mostPopularTvShowRepository = MostPopularTvShowRepository()
    }

    fun getMostPopularTvShows(page:Int):LiveData<TvShowsResponse>{
        return mostPopularTvShowRepository.getMostPopularTvShows(page)
    }

}