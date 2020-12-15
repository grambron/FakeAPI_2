package com.example.fakeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loading.visibility = View.VISIBLE

        val viewManager = LinearLayoutManager(this)
        val postAdapter = PostAdapter {
            onDeletePressed(it)
        }

        launch{
            MyApp.instance.postViewModel.getAllPosts().observe(this@MainActivity, Observer<List<Post>> {
                postAdapter.setData(it)
            })
        }

        loading.visibility = View.GONE
        postsLayout.apply {
            visibility = View.VISIBLE
            layoutManager = viewManager
            adapter = postAdapter
        }

        addButton.setOnClickListener {
            onAddPressed()
        }

        syncButton.setOnClickListener {
            sync()
        }

        if (!MyApp.instance.synced) {
            sync()
            MyApp.instance.synced = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    private fun sync() {
        launch{
            try{
                val posts = MyApp.instance.repository.getPosts()
                MyApp.instance.postViewModel.resetPosts(posts)
                Log.d("TAG", "Sync done")
                showMessage("Sync done", Toast.LENGTH_LONG)
            } catch (_: Exception) {
                Log.d("TAG", "Sync failed")
                showMessage("Sync failed", Toast.LENGTH_LONG)
            }
        }
    }

    private fun onDeletePressed(index: Int) {
        Log.d("TAG", "Pressed delete")
        launch {
            try{
                MyApp.instance.repository.deletePost(index)
                MyApp.instance.postViewModel.deletePost(index)
                Log.d("TAG", "Deleted")
                showMessage("Deleted", Toast.LENGTH_SHORT)
            } catch (_: Exception) {
                Log.d("TAG", "Failed to remove")
                showMessage("Failed to remove", Toast.LENGTH_LONG)
            }
        }
    }

    private fun onAddPressed() {
        val post = Post(0, 1, "Sample title", "Sample text")
        launch {
            try{
                MyApp.instance.repository.addPost(post)
                MyApp.instance.postViewModel.insertPost(post)
                Log.d("TAG", "Added")
                showMessage("Added", Toast.LENGTH_SHORT)
            } catch (_: Exception) {
                Log.d("TAG", "Failed to add")
                showMessage("Failed to add", Toast.LENGTH_LONG)
            }
        }
    }

    private fun showMessage(msg: String, length: Int) {
        Toast.makeText(
                this@MainActivity,
                msg,
                length
        ).show()
    }
}