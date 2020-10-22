package com.example.paginglibraryfeature.ui.posts

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.paginglibraryfeature.api.ApiClient
import com.example.paginglibraryfeature.api.ApiService
import com.example.paginglibraryfeature.api.response.RedditResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class PostsDataSource(coroutineContext: CoroutineContext): PageKeyedDataSource<String, RedditResponse.Post>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RedditResponse.Post>) {
        scope.launch {
            try {
                val response = apiService
                    .fetchPosts(loadSize = params.requestedLoadSize)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val post = listing?.children?.map { it.post }
                        callback.onResult(post ?: listOf(), listing?.before, listing?.after)
                    }
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data ${exception.message}")
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, RedditResponse.Post>) {
        scope.launch {
            try {
                val response = apiService
                    .fetchPosts(loadSize = params.requestedLoadSize, after = params.key)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val items = listing?.children?.map { it.post }
                        callback.onResult(items ?: listOf(), listing?.after)
                    }
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data ${exception.message}")
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditResponse.Post>) {
        scope.launch {
            try {
                val response = apiService
                    .fetchPosts(loadSize = params.requestedLoadSize, before = params.key)
                when {
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val items = listing?.children?.map { it.post     }
                        callback.onResult(items ?: listOf(), listing?.after)
                    }
                }
            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data ${exception.message}")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}