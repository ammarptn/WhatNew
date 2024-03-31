package com.ammarptn.whatsnew.api.model

data class DataResponse(
    val date: String,
    val title: String,
    val data: List<Data>,
)