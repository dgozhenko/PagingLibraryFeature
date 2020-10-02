package com.example.paginglibraryfeature.util

import androidx.recyclerview.widget.DiffUtil
import com.example.paginglibraryfeature.api_model.RedditPost

class DiffUtilCallback: DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.title == newItem.title && oldItem.score == newItem.score
                && oldItem.commentCount == newItem.commentCount
    }

}