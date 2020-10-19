package com.example.tvshowtracker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowtracker.repository.TvShowDetailsRepository
import com.example.tvshowtracker.responses.TvShowDetailsResponse

class TvShowDetailsViewModel() :ViewModel(){
    private lateinit var tvShowDetailsRepository: TvShowDetailsRepository

    init {
        tvShowDetailsRepository = TvShowDetailsRepository()
    }

   fun getTvShowDetails(tvShowId:String):LiveData<TvShowDetailsResponse>{
       return tvShowDetailsRepository.getTvShowDetails(tvShowId)
   }
}