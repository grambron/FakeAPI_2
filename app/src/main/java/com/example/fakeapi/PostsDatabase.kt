package com.example.fakeapi

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fakeapi.Post
import com.example.fakeapi.PostDao

@Database(entities = [Post::class], version = 1)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}