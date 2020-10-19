package com.example.tvshowtracker.responses

import com.example.tvshowtracker.models.TvShowDetails
import com.google.gson.annotations.SerializedName

data class TvShowDetailsResponse (
    @SerializedName("tvShow")
    val tvShowDetails: TvShowDetails? = null
)