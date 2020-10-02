package com.example.paginglibraryfeature.post_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.paginglibraryfeature.api_model.RedditPost
import com.example.paginglibraryfeature.data_source.PostsDataSource
import kotlinx.coroutines.Dispatchers

class PostViewModel: ViewModel() {
    var postLiveData: LiveData<PagedList<RedditPost>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        postLiveData = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<RedditPost>> = postLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, RedditPost> {
        val dataSourceFactory = object: DataSource.Factory<String, RedditPost>() {
            override fun create(): DataSource<String, RedditPost> {
                return PostsDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}