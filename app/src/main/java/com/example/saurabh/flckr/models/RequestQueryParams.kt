package com.example.saurabh.flckr.models

data class RequestQueryParams(
    var method: String = "flickr.people.getPublicPhotos",
    var apiKey: String = "227be805b3d6e934926d049533bb938a",
    var userId: String = "135487628@N06",
    var format: String = "json",
    var callback: String = "1"
)

