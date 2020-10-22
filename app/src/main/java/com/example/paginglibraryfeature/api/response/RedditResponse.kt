package com.example.paginglibraryfeature.api.response

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("data")
    val data: Listing
) {

    data class Listing(
        @SerializedName("children")
        val children: List<Child>,
        @SerializedName("after")
        val after: String?,
        @SerializedName("before")
        val before: String?
    )

    data class Child(
        @SerializedName("data")
        val post: Post
    )

    data class Post(
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
}