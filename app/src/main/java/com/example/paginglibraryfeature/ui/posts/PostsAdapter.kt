package com.example.paginglibraryfeature.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibraryfeature.R
import com.example.paginglibraryfeature.api.response.RedditResponse
import kotlinx.android.synthetic.main.adapter_row.view.*

class PostsAdapter: PagedListAdapter<RedditResponse.Post, PostsAdapter.PostViewHolder>(
    DiffUtilCallback()
) {

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

        fun bindPost(post: RedditResponse.Post) {
            with(post) {
                scoreText.text = score.toString()
                commentText.text = commentCount.toString()
                titleText.text = title
            }
        }
    }

    class DiffUtilCallback: DiffUtil.ItemCallback<RedditResponse.Post>() {
        override fun areItemsTheSame(oldItem: RedditResponse.Post, newItem: RedditResponse.Post): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: RedditResponse.Post, newItem: RedditResponse.Post): Boolean {
            return oldItem.title == newItem.title && oldItem.score == newItem.score
                    && oldItem.commentCount == newItem.commentCount
        }

    }
}