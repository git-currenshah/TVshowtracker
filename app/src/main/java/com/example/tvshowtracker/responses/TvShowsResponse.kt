package com.example.tvshowtracker.responses

import com.example.tvshowtracker.models.TvShow
import com.google.gson.annotations.SerializedName

class TvShowsResponse {
    @SerializedName("page")
    var page:Int = 0

    @SerializedName("pages")
    var totalPages:Int = 0

    @SerializedName("tv_shows")
    var tvShows:List<TvShow>? = null

}