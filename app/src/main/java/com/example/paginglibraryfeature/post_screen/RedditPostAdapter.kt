package com.example.paginglibraryfeature.post_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibraryfeature.R
import com.example.paginglibraryfeature.api_model.RedditPost
import com.example.paginglibraryfeature.util.DiffUtilCallback
import kotlinx.android.synthetic.main.adapter_row.view.*

class RedditPostAdapter: PagedListAdapter<RedditPost, RedditPostAdapter.PostViewHolder>(DiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindPost(it)
        }
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val scoreText = itemView.score
        private val commentText = itemView.comments
        private val titleText = itemView.title

        fun bindPost(redditPost: RedditPost) {
            with(redditPost) {
                scoreText.text = score.toString()
                commentText.text = commentCount.toString()
                titleText.text = title
            }
        }
    }
}