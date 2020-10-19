package com.example.tvshowtracker.models

import com.google.gson.annotations.SerializedName

data class Episode (
    @SerializedName("season")
    val season:String? = null,
    @SerializedName("episode")
    val episode:String? = null,
    @SerializedName("name")
    val name:String?=null,
    @SerializedName("air_date")
    val airDate:String? = null
)
