package com.example.paginglibraryfeature.ui.posts

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginglibraryfeature.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_recycler_view.*

class PostsActivity : AppCompatActivity() {
    private val redditPostAdapter = PostsAdapter()
    lateinit var postViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        postViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        swipeToRefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.colorPrimary))
        swipeToRefresh.setColorSchemeColors(Color.WHITE)

        swipeToRefresh.setOnRefreshListener {
            observeLiveData()
            initializeList()
            swipeToRefresh.isRefreshing = false
        }

        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        postViewModel.postLiveData.observe(this, {
            redditPostAdapter.submitList(it)
        })
    }

    private fun initializeList() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = redditPostAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
 }