package com.example.saurabh.flckr.models


data class PhotoSizes(
    var label: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var source: String? = null,
    var url: String? = null,
    var media: String? = null,
    var photoId: Long = -1
)

