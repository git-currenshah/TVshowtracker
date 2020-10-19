package com.example.tvshowtracker.models

import com.google.gson.annotations.SerializedName

data class TvShowDetails(
    @SerializedName("url")
    val url:String? = null,
    @SerializedName("description")
    val description :String? = null,
    @SerializedName("runtime")
    val runtime:String? = null,
    @SerializedName("image_path")
    val imagePath:String? = null,
    @SerializedName("rating")
    val rating:String? = null,
    @SerializedName("genres")
    val genres:ArrayList<String>? =null,
    @SerializedName("pictures")
    val pictures:ArrayList<String>? = null,
    @SerializedName("episodes")
    val episodes:List<Episode>? = null
)