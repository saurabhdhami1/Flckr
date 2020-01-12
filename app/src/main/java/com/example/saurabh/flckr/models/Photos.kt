package com.example.saurabh.flckr.models

import com.example.saurabh.flckr.models.Photo

data class Photos(
    var page: Int? = null,
    var pages: Int? = null,
    var perpage: Int? = null,
    var total: String? = null,
    var photo: List<Photo>? = null
)

