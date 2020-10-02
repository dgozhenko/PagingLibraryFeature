package com.example.paginglibraryfeature.model

import com.google.gson.annotations.SerializedName

data class RedditPost(
    @SerializedName("name")
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("num_comments")
    val commentCount: Int
)