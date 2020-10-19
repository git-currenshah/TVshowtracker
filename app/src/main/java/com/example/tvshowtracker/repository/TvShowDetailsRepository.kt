package com.example.tvshowtracker.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowtracker.models.TvShowDetails
import com.example.tvshowtracker.network.ApiClient
import com.example.tvshowtracker.network.ApiService
import com.example.tvshowtracker.responses.TvShowDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailsRepository {

    private lateinit var apiService:ApiService

    constructor(){
        apiService = ApiClient.retrofit.create(ApiService::class.java)
    }

    fun getTvShowDetails(tvShowId: String):LiveData<TvShowDetailsResponse>{
        val data: MutableLiveData<TvShowDetailsResponse> = MutableLiveData()
        apiService.getTvShowDetails(tvShowId).enqueue(object : Callback<TvShowDetailsResponse>{
            override fun onResponse(
                @NonNull call: Call<TvShowDetailsResponse>,
                @NonNull response: Response<TvShowDetailsResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(@NonNull call: Call<TvShowDetailsResponse>, @NonNull t: Throwable) {
                data.value = null
            }

        })
        return data
    }
}