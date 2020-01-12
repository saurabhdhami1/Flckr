package com.example.saurabh.flckr.data.remote.models

import com.example.saurabh.flckr.data.cache.base.MemCacheList
import com.example.saurabh.flckr.models.PhotoSizes

class ImagesCache : MemCacheList<PhotoSizes>(Comparator { s1, s2 ->
    val x1 = s1.label + s1.photoId
    val x2 = s2.label + s2.photoId
    x1.compareTo(x2)
}) {
    fun findSizeByLabel(label: String, photoId: Long) =
        list.find { it.label == label && it.photoId == photoId }

    fun getImagesByLabel(label: String) = list.filter { it.label == label }
}