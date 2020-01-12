package com.example.saurabh.flckr.data.cache.base

import com.example.saurabh.flckr.data.cache.models.ItemChange
import com.example.saurabh.flckr.data.cache.models.ItemChange.Companion.ITEM_ADD
import com.example.saurabh.flckr.data.cache.models.ItemChange.Companion.ITEM_REMOVE
import com.example.saurabh.flckr.data.cache.models.ItemChange.Companion.ITEM_UPDATE
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.CopyOnWriteArrayList

abstract class MemCacheList<T>(private val comparator: Comparator<T>) {

    protected var list: MutableList<T> = CopyOnWriteArrayList<T>()

    private val subject = PublishSubject.create<ItemChange<T>>()

    fun get() = list

    fun put(item: T) {
        var itemChange: Int = ITEM_UPDATE
        var index = list.binarySearch(item, comparator)
        // this check is to prevent crash when index is greater and equal to list size
        if (index != 0 && index >= list.size) return

        if (index < 0) { //not found
            list.add(item)
            list.sortWith(comparator)
            index = list.binarySearch(item, comparator)
            itemChange = ITEM_ADD
        } else { //update
            list[index] = item
        }
        val change = ItemChange(itemChange, index, item)
        subject.onNext(change)
    }

    fun set(index: Int, item: T) {
        list[index] = item
        val change = ItemChange(ITEM_UPDATE, index, item)
        subject.onNext(change)
    }

    fun remove(item: T) {
        val index = list.binarySearch(item, comparator)
        if (index < 0) return
        list.removeAt(index)
        val change = ItemChange(ITEM_REMOVE, index, item)
        subject.onNext(change)
    }

    fun getUpdates() = subject

    fun clear() {
        val itemsToDelete = mutableListOf<T>()
        for (item in list)
            itemsToDelete.add(item)
        for (item in itemsToDelete) {
            remove(item)
        }
    }
}