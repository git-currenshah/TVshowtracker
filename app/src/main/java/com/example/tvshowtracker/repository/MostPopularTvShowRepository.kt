package com.example.tvshowtracker.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowtracker.network.ApiClient
import com.example.tvshowtracker.network.ApiService
import com.example.tvshowtracker.responses.TvShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

class MostPopularTvShowRepository {

    private var apiService:ApiService

    constructor(){

        apiService = ApiClient.retrofit.create(ApiService::class.java) ?: throw NullPointerException("Null Retrofit")
    }

    fun getMostPopularTvShows(page:Int):LiveData<TvShowsResponse>{

        val data: MutableLiveData<TvShowsResponse> = MutableLiveData()
        val call = apiService.mostPopularTvShows(page)
        call.enqueue(object: Callback<TvShowsResponse>{
            override fun onResponse(
                @NonNull call: Call<TvShowsResponse>,
                @NonNull response: Response<TvShowsResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(@NonNull call: Call<TvShowsResponse>,@NonNull t: Throwable) {
                data.value = null
            }

        })
        return data
        }
    }