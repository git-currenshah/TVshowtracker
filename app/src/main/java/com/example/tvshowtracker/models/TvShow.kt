package com.example.tvshowtracker.models

import com.google.gson.annotations.SerializedName

class TvShow() {

    @SerializedName("name")
    var name: String? =null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("id")
    var id: Int = 0
        get() = field
        set(value) {
            field = value
        }

    @SerializedName ("start_date")
    var startDate:String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("country")
    var country:String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("network")
    var network:String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("status")
    var status:String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("image_thumbnail_path")
    var thumbnail:String? = null
        get() = field
        set(value) {
            field = value
        }


}