package com.example.saurabh.flckr.models

import com.example.saurabh.flckr.models.PhotoSizes
import com.google.gson.annotations.SerializedName


data class Sizes(
    var canblog: Int? = null,
    var canprint: Int? = null,
    var candownload: Int? = null,
    @SerializedName("size")
    var size: List<PhotoSizes>? = null
)