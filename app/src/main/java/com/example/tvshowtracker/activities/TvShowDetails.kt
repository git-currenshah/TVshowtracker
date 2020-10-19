package com.example.tvshowtracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.tvshowtracker.R
import com.example.tvshowtracker.adapters.ImageSliderAdapter
import com.example.tvshowtracker.databinding.ActivityTvShowDetailsBinding
import com.example.tvshowtracker.viewmodels.TvShowDetailsViewModel

class TvShowDetails : AppCompatActivity() {

    private lateinit var actvityTvShowDetailsBinding: ActivityTvShowDetailsBinding
    private lateinit var tvShowDetailsViewModel: TvShowDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actvityTvShowDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details)
        doInitialization()
    }

    fun doInitialization() {
        tvShowDetailsViewModel = ViewModelProvider(this).get(TvShowDetailsViewModel::class.java)
        getTvShowDetails()
    }

    private fun getTvShowDetails() {
        actvityTvShowDetailsBinding.isLoading = true
        val tvShowId: String = intent.getIntExtra("id", -1).toString()
        tvShowDetailsViewModel.getTvShowDetails(tvShowId)
            .observe(this, Observer { tvShowDetailsresponse ->
                actvityTvShowDetailsBinding.isLoading = false
                if (tvShowDetailsresponse.tvShowDetails != null) {
                    if (tvShowDetailsresponse.tvShowDetails.pictures != null) {
                        loadImageSlider(tvShowDetailsresponse.tvShowDetails.pictures)
                    }
                }
            })
    }

    fun loadImageSlider(sliderImages: ArrayList<String>) {
        actvityTvShowDetailsBinding.sliderViewPager.offscreenPageLimit = 1
        actvityTvShowDetailsBinding.sliderViewPager.adapter = ImageSliderAdapter(sliderImages)
        actvityTvShowDetailsBinding.sliderViewPager.visibility = View.VISIBLE
        actvityTvShowDetailsBinding.viewFadingEdge.visibility = View.VISIBLE
        setupSliderIndicators(sliderImages.size)
        actvityTvShowDetailsBinding.sliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentSliderIndicator(position)
            }
        })
    }

    fun setupSliderIndicators(count: Int) {
        var indicators = MutableList(count, { ImageView(applicationContext) })
        val layoutParameters = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParameters.setMargins(8, 0, 8, 0)

        for (i in 0 until count step 1) {
            indicators.get(i).setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.background_slider_indicator_inactive
                )
            )
            indicators.get(i).layoutParams = layoutParameters
            actvityTvShowDetailsBinding.layoutSliderIndicator.addView(indicators.get(i))
        }
        actvityTvShowDetailsBinding.layoutSliderIndicator.visibility = View.VISIBLE

        setCurrentSliderIndicator(0)

    }

    fun setCurrentSliderIndicator(position: Int) {
        val childCount: Int = actvityTvShowDetailsBinding.layoutSliderIndicator.childCount
        for (i in 0 until childCount step 1) {
            var imageView =
                (actvityTvShowDetailsBinding.layoutSliderIndicator.getChildAt(i) as ImageView)
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.background_slider_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.background_slider_indicator_inactive
                    )
                )
            }

        }

    }
}