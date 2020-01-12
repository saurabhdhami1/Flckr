package com.example.saurabh.flckr.data.cache.base

import io.reactivex.subjects.PublishSubject

abstract class MemCache<T>(private var item: T) {

    private val subject = PublishSubject.create<T>()

    fun get() = item

    open fun update(item: T) {
        this.item = item
        subject.onNext(item)
    }

    fun listen() = subject
}