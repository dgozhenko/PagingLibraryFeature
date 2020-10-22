package com.example.paginglibraryfeature.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.paginglibraryfeature.api.response.RedditResponse
import kotlinx.coroutines.Dispatchers

class PostsViewModel: ViewModel() {
    var postLiveData: LiveData<PagedList<RedditResponse.Post>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        postLiveData = initializedPagedListBuilder(config).build()
    }



    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, RedditResponse.Post> {
        val dataSourceFactory = object: DataSource.Factory<String, RedditResponse.Post>() {
            override fun create(): DataSource<String, RedditResponse.Post> {
                return PostsDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}