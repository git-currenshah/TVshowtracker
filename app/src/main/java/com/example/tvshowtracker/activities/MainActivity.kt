package com.example.tvshowtracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowtracker.R
import com.example.tvshowtracker.adapters.TVShowsAdapter
import com.example.tvshowtracker.databinding.ActivityMainBinding
import com.example.tvshowtracker.listeners.TvShowListner
import com.example.tvshowtracker.models.TvShow
import com.example.tvshowtracker.viewmodels.MostPopularTvShowsViewModel

class MainActivity : AppCompatActivity(), TvShowListner {

    private lateinit var viewModel:MostPopularTvShowsViewModel

    private lateinit var activityMainBinding:ActivityMainBinding

    private var tvShows :List<TvShow> = ArrayList()

    private lateinit var tvShowAdapter:TVShowsAdapter

    private var currentPage:Int = 1

    private var totalAvailablePages:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityMainBinding.mainRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!activityMainBinding.mainRecyclerView.canScrollVertically(1)){
                    if (currentPage <= totalAvailablePages){
                        currentPage+=1
                        getMostPopularTvShows()
                    }
                }
            }
        })
        doInitialization()

    }

    fun doInitialization(){
        activityMainBinding.mainRecyclerView.setHasFixedSize(true)
        tvShowAdapter = TVShowsAdapter(tvShows, this)
        activityMainBinding.mainRecyclerView.adapter = tvShowAdapter
        viewModel = ViewModelProvider(this)[MostPopularTvShowsViewModel::class.java]
        getMostPopularTvShows()
    }

    fun getMostPopularTvShows(){
        toggleLoading()
        viewModel.getMostPopularTvShows(currentPage).observe(this, { mostPopularTvShowsResponse ->

           toggleLoading()

            if (mostPopularTvShowsResponse != null){
                totalAvailablePages = mostPopularTvShowsResponse.totalPages
                if (mostPopularTvShowsResponse.tvShows != null){

                    val oldCount:Int = tvShows.size
                    (tvShows as MutableList).addAll(mostPopularTvShowsResponse.tvShows!!)

                    tvShowAdapter.notifyItemRangeInserted(oldCount,tvShows.size)
                }
            }
        })
    }

    fun toggleLoading(){
        if (currentPage == 1){
            if (activityMainBinding.isLoading != null && activityMainBinding.isLoading as Boolean){
                activityMainBinding.isLoading = false
            }else{
                activityMainBinding.isLoading = true
            }
        }else{

            if (activityMainBinding.isLoadingMore != null && activityMainBinding.isLoadingMore as Boolean){
                activityMainBinding.isLoadingMore = false
            }else{
                activityMainBinding.isLoadingMore = true
            }

        }
    }

    override fun onTvShowClicked(tvShow: TvShow) {
        val intent = Intent(applicationContext, TvShowDetails::class.java)
        intent.putExtra("id",tvShow.id)
        intent.putExtra("name",tvShow.name)
        intent.putExtra("startDate",tvShow.startDate)
        intent.putExtra("country",tvShow.country)
        intent.putExtra("network",tvShow.network)
        intent.putExtra("status",tvShow.status)
        startActivity(intent)
    }
}