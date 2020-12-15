package com.example.fakeapi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PostViewModel(
    application: Application,
    private val postsDatabase: PostsDatabase = Room
        .databaseBuilder(application.applicationContext,
            PostsDatabase::class.java,
            "posts-database")
        .build()
) : AndroidViewModel(application) {
    fun getAllPosts() = postsDatabase.postDao().findAll()

    suspend fun insertPost(post: Post) =
        postsDatabase.postDao().insert(post)


    suspend fun resetPosts(posts: List<Post>) {
        postsDatabase.postDao().deleteAll()
        postsDatabase.postDao().insertAll(posts)
    }

    suspend fun deletePost(id: Int) =
        postsDatabase.postDao().delete(id)

}
