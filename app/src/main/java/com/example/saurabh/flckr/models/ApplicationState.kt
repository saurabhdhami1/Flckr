package com.example.saurabh.flckr.models


class ApplicationState {

    var keywordsList:MutableList<String> = mutableListOf()

    fun clearState() {
        keywordsList = mutableListOf()
    }
}
