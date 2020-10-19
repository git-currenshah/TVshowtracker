package com.example.tvshowtracker.network

import com.example.tvshowtracker.responses.TvShowDetailsResponse
import com.example.tvshowtracker.responses.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("most-popular")
    fun mostPopularTvShows(@Query("page")
                           page:Int): Call<TvShowsResponse>

    @GET("show-details")
    fun getTvShowDetails(@Query("q")
                         tvShowId:String):Call<TvShowDetailsResponse>
}