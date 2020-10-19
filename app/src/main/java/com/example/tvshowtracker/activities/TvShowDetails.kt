package com.example.tvshowtracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tvshowtracker.R
import com.example.tvshowtracker.databinding.ActivityTvShowDetailsBinding
import com.example.tvshowtracker.viewmodels.TvShowDetailsViewModel

class TvShowDetails : AppCompatActivity() {

    private lateinit var actvityTvShowDetailsBinding: ActivityTvShowDetailsBinding
    private lateinit var tvShowDetailsViewModel: TvShowDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actvityTvShowDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_tv_show_details)
        doInitialization()
    }

    fun doInitialization(){
        tvShowDetailsViewModel = ViewModelProvider(this).get(TvShowDetailsViewModel::class.java)
        getTvShowDetails()
    }

    private fun getTvShowDetails(){
        actvityTvShowDetailsBinding.isLoading = true
        val tvShowId:String = intent.getIntExtra("id",-1).toString()
        tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(this, Observer { tvShowDetailsresponse ->
            actvityTvShowDetailsBinding.isLoading = false
            Toast.makeText(applicationContext, tvShowDetailsresponse.tvShowDetails?.url,Toast.LENGTH_LONG).show()

        })
    }
}