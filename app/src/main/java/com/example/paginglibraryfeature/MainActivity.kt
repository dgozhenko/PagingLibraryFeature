package com.example.paginglibraryfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibraryfeature.post_screen.PostViewModel
import com.example.paginglibraryfeature.post_screen.RedditPostAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_recycler_view.*

class MainActivity : AppCompatActivity() {
    private val redditPostAdapter = RedditPostAdapter()
    lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        postViewModel.postLiveData.observe(this, Observer {
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